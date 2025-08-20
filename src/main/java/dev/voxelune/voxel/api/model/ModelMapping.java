package dev.voxelune.voxel.api.model;

import org.bukkit.NamespacedKey;

/**
 * Represents a fallback mapping for models.
 */
public record ModelMapping(
    NamespacedKey primary,
    NamespacedKey fallback,
    String condition
) {
    
    public static ModelMapping of(NamespacedKey primary, NamespacedKey fallback) {
        return new ModelMapping(primary, fallback, "default");
    }
    
    public static ModelMapping withCondition(NamespacedKey primary, NamespacedKey fallback, String condition) {
        return new ModelMapping(primary, fallback, condition);
    }
}