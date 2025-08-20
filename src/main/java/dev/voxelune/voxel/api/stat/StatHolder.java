package dev.voxelune.voxel.api.stat;

import org.bukkit.NamespacedKey;

import java.util.Map;
import java.util.Optional;

/**
 * Holds stat values and modifiers for an entity.
 */
public interface StatHolder {
    
    /**
     * Get the base value of a stat.
     */
    double getBaseValue(NamespacedKey statKey);
    
    /**
     * Set the base value of a stat.
     */
    void setBaseValue(NamespacedKey statKey, double value);
    
    /**
     * Get the final calculated value of a stat (including modifiers).
     */
    double getFinalValue(NamespacedKey statKey);
    
    /**
     * Add a modifier to a stat.
     */
    void addModifier(NamespacedKey statKey, StatModifier modifier);
    
    /**
     * Remove a modifier from a stat.
     */
    boolean removeModifier(NamespacedKey statKey, NamespacedKey modifierKey);
    
    /**
     * Get all modifiers for a stat.
     */
    Map<NamespacedKey, StatModifier> getModifiers(NamespacedKey statKey);
    
    /**
     * Get all stats this holder has values for.
     */
    Map<NamespacedKey, Double> getAllStats();
    
    /**
     * Check if this holder has a specific stat.
     */
    boolean hasStat(NamespacedKey statKey);
    
    /**
     * Get a specific modifier.
     */
    Optional<StatModifier> getModifier(NamespacedKey statKey, NamespacedKey modifierKey);
}