package dev.voxelune.voxel.api.definitions;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

/**
 * Definition for a custom block.
 */
public record BlockDef(
    NamespacedKey key,
    String displayName,
    Material baseMaterial,
    int customModelData,
    BlockBehavior behavior
) {
    
    public static Builder builder(NamespacedKey key) {
        return new Builder(key);
    }
    
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        private Material baseMaterial = Material.STONE;
        private int customModelData = 0;
        private BlockBehavior behavior = BlockBehavior.NONE;
        
        public Builder(NamespacedKey key) {
            this.key = key;
            this.displayName = key.getKey();
        }
        
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder baseMaterial(Material material) { this.baseMaterial = material; return this; }
        public Builder customModelData(int data) { this.customModelData = data; return this; }
        public Builder behavior(BlockBehavior behavior) { this.behavior = behavior; return this; }
        
        public BlockDef build() {
            return new BlockDef(key, displayName, baseMaterial, customModelData, behavior);
        }
    }
}