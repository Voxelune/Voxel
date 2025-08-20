package dev.voxelune.voxel.api.stat;

import org.bukkit.NamespacedKey;

/**
 * Represents a modification to a stat value.
 */
public record StatModifier(
    NamespacedKey key,
    String name,
    ModifierType type,
    double value,
    int priority,
    String source
) {
    
    public static Builder builder(NamespacedKey key) {
        return new Builder(key);
    }
    
    public static class Builder {
        private final NamespacedKey key;
        private String name;
        private ModifierType type = ModifierType.ADDITIVE;
        private double value = 0.0;
        private int priority = 0;
        private String source = "unknown";
        
        public Builder(NamespacedKey key) {
            this.key = key;
            this.name = key.getKey();
        }
        
        public Builder name(String name) { this.name = name; return this; }
        public Builder type(ModifierType type) { this.type = type; return this; }
        public Builder value(double value) { this.value = value; return this; }
        public Builder priority(int priority) { this.priority = priority; return this; }
        public Builder source(String source) { this.source = source; return this; }
        
        public StatModifier build() {
            return new StatModifier(key, name, type, value, priority, source);
        }
    }
}