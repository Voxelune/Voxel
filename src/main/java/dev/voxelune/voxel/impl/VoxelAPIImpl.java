package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.*;

/**
 * Main implementation of the Voxel API.
 */
public class VoxelAPIImpl implements VoxelAPI {
    
    private final VoxelPlugin plugin;
    private final RegistryAPIImpl registryAPI;
    private final MechanicAPIImpl mechanicAPI;
    private final CapabilityAPIImpl capabilityAPI;
    private final ModelAPIImpl modelAPI;
    private final StatAPIImpl statAPI;

    public VoxelAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.registryAPI = new RegistryAPIImpl(plugin);
        this.capabilityAPI = new CapabilityAPIImpl(plugin);
        this.statAPI = new StatAPIImpl(plugin);
        this.modelAPI = new ModelAPIImpl(plugin);
        this.mechanicAPI = new MechanicAPIImpl(plugin);
    }

    @Override
    public RegistryAPI getRegistryAPI() {
        return registryAPI;
    }

    @Override
    public MechanicAPI getMechanicAPI() {
        return mechanicAPI;
    }

    @Override
    public CapabilityAPI getCapabilityAPI() {
        return capabilityAPI;
    }

    @Override
    public ModelAPI getModelAPI() {
        return modelAPI;
    }

    @Override
    public StatAPI getStatAPI() {
        return statAPI;
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean isReady() {
        return plugin.getCore() != null && plugin.getCore().isInitialized();
    }
}