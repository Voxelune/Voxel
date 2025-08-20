package dev.voxelune.voxel.api;

import dev.voxelune.voxel.api.mechanic.Mechanic;
import dev.voxelune.voxel.api.mechanic.MechanicContext;
import dev.voxelune.voxel.api.mechanic.MechanicResult;
import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.Set;

/**
 * API for compiling and running mechanic graphs with context.
 */
public interface MechanicAPI {
    
    /**
     * Load and compile a mechanic from file.
     */
    Optional<Mechanic> loadMechanic(NamespacedKey key);
    
    /**
     * Get a compiled mechanic by key.
     */
    Optional<Mechanic> getMechanic(NamespacedKey key);
    
    /**
     * Execute a mechanic with the given context.
     */
    MechanicResult executeMechanic(NamespacedKey key, MechanicContext context);
    
    /**
     * Execute a mechanic directly.
     */
    MechanicResult executeMechanic(Mechanic mechanic, MechanicContext context);
    
    /**
     * Get all loaded mechanics.
     */
    Set<NamespacedKey> getLoadedMechanics();
    
    /**
     * Reload all mechanics from disk.
     */
    void reloadMechanics();
    
    /**
     * Check if hot reload is enabled.
     */
    boolean isHotReloadEnabled();
}