package dev.voxelune.voxel.api.model;

import org.bukkit.NamespacedKey;

import java.util.List;
import java.util.Map;

/**
 * Represents a custom model with associated resources.
 */
public record VoxelModel(
    NamespacedKey key,
    String displayName,
    ModelType type,
    String resourcePath,
    List<String> animations,
    List<String> sounds,
    List<String> particles,
    Map<String, Object> properties
) {
    
    public static Builder builder(NamespacedKey key) {
        return new Builder(key);
    }
    
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        private ModelType type = ModelType.CUSTOM;
        private String resourcePath;
        private List<String> animations = List.of();
        private List<String> sounds = List.of();
        private List<String> particles = List.of();
        private Map<String, Object> properties = Map.of();
        
        public Builder(NamespacedKey key) {
            this.key = key;
            this.displayName = key.getKey();
        }
        
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder type(ModelType type) { this.type = type; return this; }
        public Builder resourcePath(String resourcePath) { this.resourcePath = resourcePath; return this; }
        public Builder animations(List<String> animations) { this.animations = List.copyOf(animations); return this; }
        public Builder sounds(List<String> sounds) { this.sounds = List.copyOf(sounds); return this; }
        public Builder particles(List<String> particles) { this.particles = List.copyOf(particles); return this; }
        public Builder properties(Map<String, Object> properties) { this.properties = Map.copyOf(properties); return this; }
        
        public VoxelModel build() {
            return new VoxelModel(key, displayName, type, resourcePath, animations, sounds, particles, properties);
        }
    }
}