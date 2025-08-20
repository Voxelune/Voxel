package dev.voxelune.voxel.api.registry;

import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * A reference to a registry entry that may not be initialized yet.
 * 
 * Similar to Forge's RegistryObject, this provides a safe way to reference
 * registered objects before they are fully initialized.
 * 
 * @param <T> the type of the registered object
 */
public interface RegistryObject<T> extends Supplier<T> {
    
    /**
     * Get the namespaced key for this registry object.
     * 
     * @return the key
     */
    NamespacedKey getKey();
    
    /**
     * Get the registered object.
     * 
     * @return the object, or throws if not present
     * @throws IllegalStateException if the object is not registered
     */
    @Override
    T get();
    
    /**
     * Get the registered object if present.
     * 
     * @return optional containing the object if registered
     */
    Optional<T> getOptional();
    
    /**
     * Check if this registry object is present.
     * 
     * @return true if the object is registered and available
     */
    boolean isPresent();
    
    /**
     * Get the registry this object belongs to.
     * 
     * @return the parent registry
     */
    VoxelRegistry<T> getRegistry();
}