package dev.voxelune.voxel.core.registry;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.registry.RegistryObject;
import dev.voxelune.voxel.api.registry.VoxelRegistry;
import org.bukkit.NamespacedKey;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Implementation of the VoxelRegistry interface.
 * 
 * This provides the actual deferred registry functionality similar to
 * Forge's DeferredRegister system.
 */
public class VoxelRegistryImpl<T> implements VoxelRegistry<T> {
    
    private final Class<T> type;
    private final VoxelPlugin plugin;
    private final Map<NamespacedKey, Supplier<T>> suppliers;
    private final Map<NamespacedKey, T> objects;
    private final Map<NamespacedKey, RegistryObject<T>> registryObjects;
    private boolean frozen = false;

    public VoxelRegistryImpl(Class<T> type, VoxelPlugin plugin) {
        this.type = type;
        this.plugin = plugin;
        this.suppliers = new ConcurrentHashMap<>();
        this.objects = new ConcurrentHashMap<>();
        this.registryObjects = new ConcurrentHashMap<>();
    }

    @Override
    public RegistryObject<T> register(String key, Supplier<T> supplier) {
        // Assume the current plugin's namespace if not specified
        String namespace = "voxel"; // Default namespace
        if (key.contains(":")) {
            return register(NamespacedKey.fromString(key), supplier);
        } else {
            return register(new NamespacedKey(namespace, key), supplier);
        }
    }

    @Override
    public RegistryObject<T> register(NamespacedKey key, Supplier<T> supplier) {
        if (frozen) {
            throw new IllegalStateException("Registry is frozen, cannot register new objects");
        }
        
        if (suppliers.containsKey(key)) {
            throw new IllegalArgumentException("Key already registered: " + key);
        }
        
        suppliers.put(key, supplier);
        RegistryObject<T> registryObject = new RegistryObjectImpl<>(key, this);
        registryObjects.put(key, registryObject);
        
        plugin.getVoxelLogger().debug("Registered " + type.getSimpleName() + ": " + key);
        return registryObject;
    }

    @Override
    public Optional<T> get(NamespacedKey key) {
        // Try to get from cache first
        T cached = objects.get(key);
        if (cached != null) {
            return Optional.of(cached);
        }
        
        // Try to create from supplier
        Supplier<T> supplier = suppliers.get(key);
        if (supplier != null) {
            try {
                T object = supplier.get();
                objects.put(key, object);
                return Optional.of(object);
            } catch (Exception e) {
                plugin.getVoxelLogger().error("Failed to create object for key " + key + ": " + e.getMessage(), e);
                return Optional.empty();
            }
        }
        
        return Optional.empty();
    }

    @Override
    public boolean contains(NamespacedKey key) {
        return suppliers.containsKey(key);
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return new HashSet<>(suppliers.keySet());
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public int size() {
        return suppliers.size();
    }

    @Override
    public boolean isEmpty() {
        return suppliers.isEmpty();
    }

    @Override
    public void freeze() {
        if (frozen) return;
        
        // Initialize all objects
        for (NamespacedKey key : suppliers.keySet()) {
            get(key); // This will create and cache the object
        }
        
        this.frozen = true;
        plugin.getVoxelLogger().debug("Frozen registry for type: " + type.getSimpleName());
    }

    @Override
    public boolean isFrozen() {
        return frozen;
    }
    
    // Package-private method for RegistryObjectImpl
    RegistryObject<T> getRegistryObject(NamespacedKey key) {
        return registryObjects.get(key);
    }
}