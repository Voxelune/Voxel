package dev.voxelune.voxel.api.definitions;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Definition for a custom item.
 * 
 * This represents a custom item that can be registered with the framework
 * and used throughout the plugin ecosystem.
 */
public record ItemDef(
    NamespacedKey key,
    String displayName,
    List<String> lore,
    Material baseMaterial,
    int customModelData,
    Map<String, Object> properties,
    ItemBehavior behavior
) {
    
    public static Builder builder(NamespacedKey key) {
        return new Builder(key);
    }
    
    /**
     * Create an ItemStack from this definition.
     * 
     * @return a new ItemStack with this item's properties
     */
    public ItemStack createItemStack() {
        return createItemStack(1);
    }
    
    /**
     * Create an ItemStack from this definition with a specific amount.
     * 
     * @param amount the stack size
     * @return a new ItemStack with this item's properties
     */
    public ItemStack createItemStack(int amount) {
        ItemStack item = new ItemStack(baseMaterial, amount);
        var meta = item.getItemMeta();
        
        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(lore);
            if (customModelData > 0) {
                meta.setCustomModelData(customModelData);
            }
            item.setItemMeta(meta);
        }
        
        return item;
    }
    
    public static class Builder {
        private final NamespacedKey key;
        private String displayName;
        private List<String> lore = List.of();
        private Material baseMaterial = Material.STICK;
        private int customModelData = 0;
        private Map<String, Object> properties = Map.of();
        private ItemBehavior behavior = ItemBehavior.NONE;
        
        public Builder(NamespacedKey key) {
            this.key = key;
            this.displayName = key.getKey();
        }
        
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder lore(List<String> lore) { this.lore = List.copyOf(lore); return this; }
        public Builder baseMaterial(Material material) { this.baseMaterial = material; return this; }
        public Builder customModelData(int data) { this.customModelData = data; return this; }
        public Builder properties(Map<String, Object> properties) { this.properties = Map.copyOf(properties); return this; }
        public Builder behavior(ItemBehavior behavior) { this.behavior = behavior; return this; }
        
        public ItemDef build() {
            return new ItemDef(key, displayName, lore, baseMaterial, customModelData, properties, behavior);
        }
    }
}