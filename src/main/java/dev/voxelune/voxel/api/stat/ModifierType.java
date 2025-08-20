package dev.voxelune.voxel.api.stat;

/**
 * Types of stat modifiers.
 */
public enum ModifierType {
    ADDITIVE,      // Adds to the base value
    MULTIPLICATIVE, // Multiplies the current value
    PERCENTAGE     // Adds a percentage of the base value
}