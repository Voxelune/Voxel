package dev.voxelune.voxel.core.model;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.model.ModelMapping;
import dev.voxelune.voxel.api.model.ModelType;
import dev.voxelune.voxel.api.model.VoxelModel;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages model registration, attachment, and fallback routing.
 */
public class ModelManager {
    
    private final VoxelPlugin plugin;
    private final Map<NamespacedKey, VoxelModel> models;
    private final Map<NamespacedKey, ModelMapping> mappings;
    private final NamespacedKey MODEL_KEY;

    public ModelManager(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.models = new ConcurrentHashMap<>();
        this.mappings = new ConcurrentHashMap<>();
        this.MODEL_KEY = new NamespacedKey(plugin, "attached_model");
    }

    public void initialize() {
        plugin.getVoxelLogger().info("Initializing model manager...");
        
        // Register a dummy model for testing
        VoxelModel testModel = VoxelModel.builder(new NamespacedKey("voxel", "test_model"))
            .displayName("Test Model")
            .type(ModelType.CUSTOM)
            .resourcePath("models/test")
            .build();
            
        registerModel(testModel);
        
        plugin.getVoxelLogger().info("Model manager initialized");
    }

    public void reload() {
        // Model reload logic would go here
        plugin.getVoxelLogger().info("Model manager reloaded");
    }

    public void shutdown() {
        models.clear();
        mappings.clear();
    }

    public void registerModel(VoxelModel model) {
        models.put(model.key(), model);
        plugin.getVoxelLogger().debug("Registered model: " + model.key());
    }

    public Optional<VoxelModel> getModel(NamespacedKey key) {
        return Optional.ofNullable(models.get(key));
    }

    public void attachModel(Entity entity, NamespacedKey modelKey) {
        entity.getPersistentDataContainer().set(MODEL_KEY, PersistentDataType.STRING, modelKey.toString());
        plugin.getVoxelLogger().debug("Attached model " + modelKey + " to entity " + entity.getUniqueId());
    }

    public void attachModel(ItemStack item, NamespacedKey modelKey) {
        if (item.hasItemMeta()) {
            var meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(MODEL_KEY, PersistentDataType.STRING, modelKey.toString());
            item.setItemMeta(meta);
        }
        plugin.getVoxelLogger().debug("Attached model " + modelKey + " to item");
    }

    public Optional<NamespacedKey> getAttachedModel(Entity entity) {
        String keyStr = entity.getPersistentDataContainer().get(MODEL_KEY, PersistentDataType.STRING);
        return keyStr != null ? Optional.of(NamespacedKey.fromString(keyStr)) : Optional.empty();
    }

    public Optional<NamespacedKey> getAttachedModel(ItemStack item) {
        if (!item.hasItemMeta()) return Optional.empty();
        
        String keyStr = item.getItemMeta().getPersistentDataContainer().get(MODEL_KEY, PersistentDataType.STRING);
        return keyStr != null ? Optional.of(NamespacedKey.fromString(keyStr)) : Optional.empty();
    }

    public void registerMapping(ModelMapping mapping) {
        mappings.put(mapping.primary(), mapping);
        plugin.getVoxelLogger().debug("Registered model mapping: " + mapping.primary() + " -> " + mapping.fallback());
    }

    public Set<NamespacedKey> getRegisteredModels() {
        return new HashSet<>(models.keySet());
    }

    public void removeModel(Entity entity) {
        entity.getPersistentDataContainer().remove(MODEL_KEY);
    }

    public void removeModel(ItemStack item) {
        if (item.hasItemMeta()) {
            var meta = item.getItemMeta();
            meta.getPersistentDataContainer().remove(MODEL_KEY);
            item.setItemMeta(meta);
        }
    }
}