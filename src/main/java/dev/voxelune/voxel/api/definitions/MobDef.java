package dev.voxelune.voxel.api.definitions;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;

/**
 * Definition for a custom mob/entity.
 */
public record MobDef(
    NamespacedKey key,
    String displayName,
    EntityType baseType,
    double health,
    double damage,
    MobBehavior behavior
) {
    
    public static Builder builder(NamespacedKey key) {
        return new Builder(key);
    }
    
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        private EntityType baseType = EntityType.ZOMBIE;
        private double health = 20.0;
        private double damage = 1.0;
        private MobBehavior behavior = MobBehavior.NONE;
        
        public Builder(NamespacedKey key) {
            this.key = key;
            this.displayName = key.getKey();
        }
        
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder baseType(EntityType type) { this.baseType = type; return this; }
        public Builder health(double health) { this.health = health; return this; }
        public Builder damage(double damage) { this.damage = damage; return this; }
        public Builder behavior(MobBehavior behavior) { this.behavior = behavior; return this; }
        
        public MobDef build() {
            return new MobDef(key, displayName, baseType, health, damage, behavior);
        }
    }
}