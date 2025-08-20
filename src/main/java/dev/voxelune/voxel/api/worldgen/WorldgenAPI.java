package dev.voxelune.voxel.api.worldgen;

/**
 * API for custom world generation.
 */
public interface WorldgenAPI {
    
    /**
     * Register a world generation feature.
     */
    void registerFeature(WorldgenFeature feature);
}