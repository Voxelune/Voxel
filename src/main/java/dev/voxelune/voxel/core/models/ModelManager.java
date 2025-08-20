package dev.voxelune.voxel.core.models;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.models.*;

/**
 * Manages custom models and resourcepack generation.
 */
public class ModelManager implements ModelAPI {
    
    private final VoxelPlugin plugin;

    public ModelManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing model manager...");
        plugin.getVoxelLogger().framework("Model manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Model manager reloaded");
    }

    public void shutdown() {
        // Cleanup
    }

    @Override
    public void registerModel(CustomModel model) {
        plugin.getVoxelLogger().debug("Registered model: " + model.getKey());
    }

    @Override
    public void generateResourcePack() {
        plugin.getVoxelLogger().framework("Generating resourcepack...");
    }

    @Override
    public boolean isAutoGenerationEnabled() {
        return plugin.getConfiguration().isAutoGeneration();
    }
}