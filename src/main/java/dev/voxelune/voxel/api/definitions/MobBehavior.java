package dev.voxelune.voxel.api.definitions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Defines behavior for custom mobs.
 */
public interface MobBehavior {
    
    MobBehavior NONE = new MobBehavior() {};
    
    default void onSpawn(Entity entity) {}
    default void onDeath(Entity entity, Player killer) {}
    default void onTarget(Entity entity, Entity target) {}
    default void onAttack(Entity entity, Entity target) {}
}