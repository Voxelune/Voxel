package dev.voxelune.voxel.core.registry;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.registry.Registry;
import dev.voxelune.voxel.api.registry.RegistryType;
import org.bukkit.NamespacedKey;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages all registries in the Voxel system.
 */
public class RegistryManager {
    
    private final VoxelPlugin plugin;
    private final Map<RegistryType<?>, Registry<?>> registries;

    public RegistryManager(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.registries = new ConcurrentHashMap<>();
    }

    public void initialize() {
        plugin.getVoxelLogger().info("Initializing registry manager...");
        
        // Create default registries
        createRegistry(RegistryType.SKILLS);
        createRegistry(RegistryType.ITEMS);
        createRegistry(RegistryType.MOBS);
        createRegistry(RegistryType.SHOPS);
        createRegistry(RegistryType.DUNGEONS);
        createRegistry(RegistryType.WORLDGEN);
        
        plugin.getVoxelLogger().info("Registry manager initialized with " + registries.size() + " registries");
    }

    public void shutdown() {
        registries.clear();
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<Registry<T>> getRegistry(RegistryType<T> type) {
        return Optional.ofNullable((Registry<T>) registries.get(type));
    }

    @SuppressWarnings("unchecked")
    public <T> Registry<T> createRegistry(RegistryType<T> type) {
        Registry<T> registry = new VoxelRegistry<>(type);
        registries.put(type, registry);
        return registry;
    }

    public Set<RegistryType<?>> getRegistryTypes() {
        return new HashSet<>(registries.keySet());
    }

    public <T> boolean hasRegistry(RegistryType<T> type) {
        return registries.containsKey(type);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getEntry(NamespacedKey key, Class<T> type) {
        for (Registry<?> registry : registries.values()) {
            Optional<?> entry = registry.get(key);
            if (entry.isPresent() && type.isInstance(entry.get())) {
                return Optional.of((T) entry.get());
            }
        }
        return Optional.empty();
    }
}