package dev.voxelune.voxel.api.registry;

import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.Set;

/**
 * Generic registry interface for storing and retrieving keyed objects.
 */
public interface Registry<T> {
    
    /**
     * Register an object with the given key.
     */
    void register(NamespacedKey key, T object);
    
    /**
     * Get an object by its key.
     */
    Optional<T> get(NamespacedKey key);
    
    /**
     * Check if a key is registered.
     */
    boolean contains(NamespacedKey key);
    
    /**
     * Remove an object by its key.
     */
    Optional<T> remove(NamespacedKey key);
    
    /**
     * Get all registered keys.
     */
    Set<NamespacedKey> getKeys();
    
    /**
     * Get the registry type.
     */
    RegistryType<T> getType();
    
    /**
     * Get the number of registered objects.
     */
    int size();
    
    /**
     * Clear all entries.
     */
    void clear();
    
    /**
     * Check if the registry is empty.
     */
    boolean isEmpty();
}