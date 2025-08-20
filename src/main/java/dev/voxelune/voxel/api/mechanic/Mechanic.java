package dev.voxelune.voxel.api.mechanic;

import org.bukkit.NamespacedKey;

/**
 * Represents a compiled mechanic that can be executed.
 */
public interface Mechanic {
    
    /**
     * Get the unique identifier for this mechanic.
     */
    NamespacedKey getId();
    
    /**
     * Get the version of this mechanic.
     */
    String getVersion();
    
    /**
     * Check if this mechanic is enabled.
     */
    boolean isEnabled();
    
    /**
     * Get the description of this mechanic.
     */
    String getDescription();
    
    /**
     * Execute this mechanic with the given context.
     */
    MechanicResult execute(MechanicContext context);
    
    /**
     * Get the mechanic's metadata.
     */
    MechanicMetadata getMetadata();
}