package dev.voxelune.voxel.api.definitions;

import org.bukkit.NamespacedKey;

public record JobDef(NamespacedKey key, String displayName) {
    public static Builder builder(NamespacedKey key) { return new Builder(key); }
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        public Builder(NamespacedKey key) { this.key = key; this.displayName = key.getKey(); }
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public JobDef build() { return new JobDef(key, displayName); }
    }
}