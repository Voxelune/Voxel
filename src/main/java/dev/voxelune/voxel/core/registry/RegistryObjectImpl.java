package dev.voxelune.voxel.core.registry;

import dev.voxelune.voxel.api.registry.RegistryObject;
import dev.voxelune.voxel.api.registry.VoxelRegistry;
import org.bukkit.NamespacedKey;

import java.util.Optional;

/**
 * Implementation of RegistryObject.
 * 
 * This provides a safe reference to registry entries that may not be
 * initialized yet, similar to Forge's RegistryObject.
 */
public class RegistryObjectImpl<T> implements RegistryObject<T> {
    
    private final NamespacedKey key;
    private final VoxelRegistry<T> registry;

    public RegistryObjectImpl(NamespacedKey key, VoxelRegistry<T> registry) {
        this.key = key;
        this.registry = registry;
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @Override
    public T get() {
        return registry.get(key).orElseThrow(() -> 
            new IllegalStateException("Registry object not present: " + key));
    }

    @Override
    public Optional<T> getOptional() {
        return registry.get(key);
    }

    @Override
    public boolean isPresent() {
        return registry.contains(key);
    }

    @Override
    public VoxelRegistry<T> getRegistry() {
        return registry;
    }
    
    @Override
    public String toString() {
        return "RegistryObject{" + key + " -> " + registry.getType().getSimpleName() + "}";
    }
}