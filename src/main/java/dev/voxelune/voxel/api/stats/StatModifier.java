package dev.voxelune.voxel.api.stats;

/**
 * Represents a stat modifier.
 */
public interface StatModifier {
    
    String getId();
    double getValue();
    ModifierType getType();
    long getDuration(); // -1 for permanent
    
    enum ModifierType {
        ADDITIVE, MULTIPLICATIVE, PERCENTAGE
    }
}