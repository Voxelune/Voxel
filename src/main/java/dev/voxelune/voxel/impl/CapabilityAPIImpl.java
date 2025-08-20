package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.CapabilityAPI;
import dev.voxelune.voxel.api.capability.Capability;
import dev.voxelune.voxel.api.capability.CapabilityProvider;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Capability API.
 */
public class CapabilityAPIImpl implements CapabilityAPI {
    
    private final VoxelPlugin plugin;

    public CapabilityAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> void registerProvider(Plugin plugin, Capability<T> capability, CapabilityProvider<T> provider) {
        this.plugin.getCore().getCapabilityManager().registerProvider(plugin, capability, provider);
    }

    @Override
    public <T> Optional<CapabilityProvider<T>> resolve(Capability<T> capability) {
        return plugin.getCore().getCapabilityManager().resolve(capability);
    }

    @Override
    public <T> Set<CapabilityProvider<T>> resolveAll(Capability<T> capability) {
        return plugin.getCore().getCapabilityManager().resolveAll(capability);
    }

    @Override
    public Set<Capability<?>> getRegisteredCapabilities() {
        return plugin.getCore().getCapabilityManager().getRegisteredCapabilities();
    }

    @Override
    public <T> boolean hasCapability(Capability<T> capability) {
        return plugin.getCore().getCapabilityManager().hasCapability(capability);
    }

    @Override
    public void unregisterPlugin(Plugin plugin) {
        this.plugin.getCore().getCapabilityManager().unregisterPlugin(plugin);
    }
}