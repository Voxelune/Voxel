package dev.voxelune.voxel.api;

import dev.voxelune.voxel.api.registry.Registry;
import dev.voxelune.voxel.api.registry.RegistryType;
import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.Set;

/**
 * API for managing typed registries with validation and namespaced keys.
 */
public interface RegistryAPI {
    
    /**
     * Get a registry by its type.
     */
    <T> Optional<Registry<T>> getRegistry(RegistryType<T> type);
    
    /**
     * Register a new registry type.
     */
    <T> Registry<T> createRegistry(RegistryType<T> type);
    
    /**
     * Get all available registry types.
     */
    Set<RegistryType<?>> getRegistryTypes();
    
    /**
     * Check if a registry exists for the given type.
     */
    <T> boolean hasRegistry(RegistryType<T> type);
    
    /**
     * Get an entry from any registry by its namespaced key.
     */
    <T> Optional<T> getEntry(NamespacedKey key, Class<T> type);
}