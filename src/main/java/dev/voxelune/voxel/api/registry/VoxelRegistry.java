package dev.voxelune.voxel.api.registry;

import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Deferred registry for plugin-defined content.
 * 
 * Similar to Forge's DeferredRegister, this allows plugins to register
 * content that will be initialized at the appropriate time during the
 * server lifecycle.
 * 
 * @param <T> the type of objects stored in this registry
 */
public interface VoxelRegistry<T> {
    
    /**
     * Register an object with a deferred supplier.
     * 
     * @param key the namespaced key for the object
     * @param supplier supplier that creates the object when needed
     * @return a registry object that can be used to access the registered item
     */
    RegistryObject<T> register(String key, Supplier<T> supplier);
    
    /**
     * Register an object with a full namespaced key.
     * 
     * @param key the full namespaced key
     * @param supplier supplier that creates the object when needed
     * @return a registry object that can be used to access the registered item
     */
    RegistryObject<T> register(NamespacedKey key, Supplier<T> supplier);
    
    /**
     * Get an object by its key.
     * 
     * @param key the namespaced key
     * @return the registered object, if present
     */
    Optional<T> get(NamespacedKey key);
    
    /**
     * Check if a key is registered.
     * 
     * @param key the namespaced key
     * @return true if the key is registered
     */
    boolean contains(NamespacedKey key);
    
    /**
     * Get all registered keys.
     * 
     * @return set of all registered keys
     */
    Set<NamespacedKey> getKeys();
    
    /**
     * Get the registry type.
     * 
     * @return the class this registry holds
     */
    Class<T> getType();
    
    /**
     * Get the number of registered objects.
     * 
     * @return the registry size
     */
    int size();
    
    /**
     * Check if the registry is empty.
     * 
     * @return true if no objects are registered
     */
    boolean isEmpty();
    
    /**
     * Freeze the registry to prevent further registrations.
     * This is called automatically during server startup.
     */
    void freeze();
    
    /**
     * Check if the registry is frozen.
     * 
     * @return true if the registry is frozen
     */
    boolean isFrozen();
}