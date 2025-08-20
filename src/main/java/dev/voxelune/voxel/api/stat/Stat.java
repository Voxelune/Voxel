package dev.voxelune.voxel.api.stat;

import org.bukkit.NamespacedKey;

/**
 * Represents a stat definition with metadata and calculation rules.
 */
public record Stat(
    NamespacedKey key,
    String displayName,
    StatType type,
    double defaultValue,
    double minValue,
    double maxValue,
    String description
) {
    
    public static Builder builder(NamespacedKey key) {
        return new Builder(key);
    }
    
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        private StatType type = StatType.ATTRIBUTE;
        private double defaultValue = 0.0;
        private double minValue = Double.NEGATIVE_INFINITY;
        private double maxValue = Double.POSITIVE_INFINITY;
        private String description = "";
        
        public Builder(NamespacedKey key) {
            this.key = key;
            this.displayName = key.getKey();
        }
        
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder type(StatType type) { this.type = type; return this; }
        public Builder defaultValue(double defaultValue) { this.defaultValue = defaultValue; return this; }
        public Builder minValue(double minValue) { this.minValue = minValue; return this; }
        public Builder maxValue(double maxValue) { this.maxValue = maxValue; return this; }
        public Builder description(String description) { this.description = description; return this; }
        
        public Stat build() {
            return new Stat(key, displayName, type, defaultValue, minValue, maxValue, description);
        }
    }
}