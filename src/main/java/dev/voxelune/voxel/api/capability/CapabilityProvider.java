package dev.voxelune.voxel.api.capability;

import org.bukkit.plugin.Plugin;

/**
 * Provides an implementation for a specific capability.
 */
public interface CapabilityProvider<T> {
    
    /**
     * Get the capability this provider implements.
     */
    Capability<T> getCapability();
    
    /**
     * Get the plugin that registered this provider.
     */
    Plugin getPlugin();
    
    /**
     * Get the implementation instance.
     */
    T getInstance();
    
    /**
     * Get the priority of this provider (higher = more preferred).
     */
    default int getPriority() { return 0; }
    
    /**
     * Check if this provider is available.
     */
    default boolean isAvailable() { return true; }
}
</btml_action>