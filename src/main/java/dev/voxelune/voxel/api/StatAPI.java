package dev.voxelune.voxel.api;

import dev.voxelune.voxel.api.stat.Stat;
import dev.voxelune.voxel.api.stat.StatHolder;
import dev.voxelune.voxel.api.stat.StatModifier;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;

import java.util.Optional;
import java.util.Set;

/**
 * API for defining stats/elements, formulas, and stacking rules.
 */
public interface StatAPI {
    
    /**
     * Register a stat definition.
     */
    void registerStat(Stat stat);
    
    /**
     * Get a stat by key.
     */
    Optional<Stat> getStat(NamespacedKey key);
    
    /**
     * Get a stat holder for an entity.
     */
    StatHolder getStatHolder(Entity entity);
    
    /**
     * Apply a stat modifier to an entity.
     */
    void applyModifier(Entity entity, StatModifier modifier);
    
    /**
     * Remove a stat modifier from an entity.
     */
    void removeModifier(Entity entity, NamespacedKey modifierKey);
    
    /**
     * Get the final value of a stat for an entity.
     */
    double getStatValue(Entity entity, NamespacedKey statKey);
    
    /**
     * Get all registered stats.
     */
    Set<NamespacedKey> getRegisteredStats();
    
    /**
     * Check if an entity has a stat.
     */
    boolean hasStat(Entity entity, NamespacedKey statKey);
}