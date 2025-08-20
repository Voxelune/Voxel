package dev.voxelune.voxel.api.mechanics;

import org.bukkit.NamespacedKey;

/**
 * API for the Mechanic DSL v3 system.
 * 
 * Provides graph-based mechanics with scripting, branching, loops, and events.
 */
public interface MechanicAPI {
    
    /**
     * Execute a mechanic by its key.
     */
    MechanicResult execute(NamespacedKey mechanicKey, MechanicContext context);
    
    /**
     * Register a custom mechanic node.
     */
    void registerNode(String nodeType, MechanicNode node);
    
    /**
     * Check if hot reload is enabled.
     */
    boolean isHotReloadEnabled();
    
    /**
     * Reload all mechanics from disk.
     */
    void reloadMechanics();
}