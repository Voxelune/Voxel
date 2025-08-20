package dev.voxelune.voxel.api;

import dev.voxelune.voxel.api.capability.Capability;
import dev.voxelune.voxel.api.capability.CapabilityProvider;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.Set;

/**
 * API for registering and resolving capabilities with versioning and type safety.
 */
public interface CapabilityAPI {
    
    /**
     * Register a capability provider.
     */
    <T> void registerProvider(Plugin plugin, Capability<T> capability, CapabilityProvider<T> provider);
    
    /**
     * Resolve a capability provider.
     */
    <T> Optional<CapabilityProvider<T>> resolve(Capability<T> capability);
    
    /**
     * Resolve all providers for a capability.
     */
    <T> Set<CapabilityProvider<T>> resolveAll(Capability<T> capability);
    
    /**
     * Get all registered capabilities.
     */
    Set<Capability<?>> getRegisteredCapabilities();
    
    /**
     * Check if a capability is registered.
     */
    <T> boolean hasCapability(Capability<T> capability);
    
    /**
     * Unregister all capabilities for a plugin.
     */
    void unregisterPlugin(Plugin plugin);
}