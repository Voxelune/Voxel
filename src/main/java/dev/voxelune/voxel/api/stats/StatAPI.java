package dev.voxelune.voxel.api.stats;

import org.bukkit.entity.Player;

/**
 * API for player statistics and modifiers.
 */
public interface StatAPI {
    
    /**
     * Get a player's stat value.
     */
    double getStatValue(Player player, String statName);
    
    /**
     * Set a player's base stat value.
     */
    void setBaseStat(Player player, String statName, double value);
    
    /**
     * Add a temporary modifier to a stat.
     */
    void addModifier(Player player, String statName, StatModifier modifier);
    
    /**
     * Remove a modifier from a stat.
     */
    void removeModifier(Player player, String statName, String modifierId);
}