package dev.voxelune.voxel.api.models;

import org.bukkit.NamespacedKey;

/**
 * Represents a custom model.
 */
public interface CustomModel {
    
    NamespacedKey getKey();
    String getModelPath();
    String[] getTextures();
    ModelType getType();
    
    enum ModelType {
        ITEM, BLOCK, ENTITY, COSMETIC
    }
}