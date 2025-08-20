package dev.voxelune.voxel.api.stat;

/**
 * Types of stats supported by Voxel.
 */
public enum StatType {
    ATTRIBUTE,     // Basic attributes like strength, intelligence
    ELEMENT,       // Elemental resistances/damage
    RESOURCE,      // Health, mana, stamina
    SKILL,         // Skill levels and experience
    COMBAT,        // Combat-related stats
    UTILITY        // Utility stats
}