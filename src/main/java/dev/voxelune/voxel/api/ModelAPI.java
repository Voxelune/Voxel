package dev.voxelune.voxel.api;

import dev.voxelune.voxel.api.model.VoxelModel;
import dev.voxelune.voxel.api.model.ModelMapping;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Set;

/**
 * API for registering models, sounds, and particles with fallback routing.
 */
public interface ModelAPI {
    
    /**
     * Register a model.
     */
    void registerModel(VoxelModel model);
    
    /**
     * Get a model by key.
     */
    Optional<VoxelModel> getModel(NamespacedKey key);
    
    /**
     * Attach a model to an entity.
     */
    void attachModel(Entity entity, NamespacedKey modelKey);
    
    /**
     * Attach a model to an item stack.
     */
    void attachModel(ItemStack item, NamespacedKey modelKey);
    
    /**
     * Get the model attached to an entity.
     */
    Optional<NamespacedKey> getAttachedModel(Entity entity);
    
    /**
     * Get the model attached to an item.
     */
    Optional<NamespacedKey> getAttachedModel(ItemStack item);
    
    /**
     * Register a model mapping for fallbacks.
     */
    void registerMapping(ModelMapping mapping);
    
    /**
     * Get all registered models.
     */
    Set<NamespacedKey> getRegisteredModels();
    
    /**
     * Remove a model attachment.
     */
    void removeModel(Entity entity);
    
    /**
     * Remove a model attachment.
     */
    void removeModel(ItemStack item);
}