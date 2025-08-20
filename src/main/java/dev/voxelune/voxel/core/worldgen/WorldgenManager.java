package dev.voxelune.voxel.core.worldgen;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.worldgen.*;

/**
 * Manages custom world generation.
 */
public class WorldgenManager implements WorldgenAPI {
    
    private final VoxelPlugin plugin;

    public WorldgenManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing worldgen manager...");
        plugin.getVoxelLogger().framework("Worldgen manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Worldgen manager reloaded");
    }

    public void shutdown() {
        // Cleanup
    }

    @Override
    public void registerFeature(WorldgenFeature feature) {
        plugin.getVoxelLogger().debug("Registered worldgen feature: " + feature.getId());
    }
}