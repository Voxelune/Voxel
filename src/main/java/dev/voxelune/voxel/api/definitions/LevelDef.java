package dev.voxelune.voxel.api.definitions;

import org.bukkit.NamespacedKey;

public record LevelDef(NamespacedKey key, String displayName) {
    public static Builder builder(NamespacedKey key) { return new Builder(key); }
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        public Builder(NamespacedKey key) { this.key = key; this.displayName = key.getKey(); }
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public LevelDef build() { return new LevelDef(key, displayName); }
    }
}