package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.ModelAPI;
import dev.voxelune.voxel.api.model.ModelMapping;
import dev.voxelune.voxel.api.model.VoxelModel;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Model API.
 */
public class ModelAPIImpl implements ModelAPI {
    
    private final VoxelPlugin plugin;

    public ModelAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerModel(VoxelModel model) {
        plugin.getCore().getModelManager().registerModel(model);
    }

    @Override
    public Optional<VoxelModel> getModel(NamespacedKey key) {
        return plugin.getCore().getModelManager().getModel(key);
    }

    @Override
    public void attachModel(Entity entity, NamespacedKey modelKey) {
        plugin.getCore().getModelManager().attachModel(entity, modelKey);
    }

    @Override
    public void attachModel(ItemStack item, NamespacedKey modelKey) {
        plugin.getCore().getModelManager().attachModel(item, modelKey);
    }

    @Override
    public Optional<NamespacedKey> getAttachedModel(Entity entity) {
        return plugin.getCore().getModelManager().getAttachedModel(entity);
    }

    @Override
    public Optional<NamespacedKey> getAttachedModel(ItemStack item) {
        return plugin.getCore().getModelManager().getAttachedModel(item);
    }

    @Override
    public void registerMapping(ModelMapping mapping) {
        plugin.getCore().getModelManager().registerMapping(mapping);
    }

    @Override
    public Set<NamespacedKey> getRegisteredModels() {
        return plugin.getCore().getModelManager().getRegisteredModels();
    }

    @Override
    public void removeModel(Entity entity) {
        plugin.getCore().getModelManager().removeModel(entity);
    }

    @Override
    public void removeModel(ItemStack item) {
        plugin.getCore().getModelManager().removeModel(item);
    }
}