package dev.voxelune.voxel.api.models;

/**
 * API for custom models and resourcepack generation.
 */
public interface ModelAPI {
    
    /**
     * Register a custom model.
     */
    void registerModel(CustomModel model);
    
    /**
     * Generate resourcepack with all registered models.
     */
    void generateResourcePack();
    
    /**
     * Check if auto-generation is enabled.
     */
    boolean isAutoGenerationEnabled();
}