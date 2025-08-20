package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.RegistryAPI;
import dev.voxelune.voxel.api.registry.Registry;
import dev.voxelune.voxel.api.registry.RegistryType;
import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Registry API.
 */
public class RegistryAPIImpl implements RegistryAPI {
    
    private final VoxelPlugin plugin;

    public RegistryAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> Optional<Registry<T>> getRegistry(RegistryType<T> type) {
        return plugin.getCore().getRegistryManager().getRegistry(type);
    }

    @Override
    public <T> Registry<T> createRegistry(RegistryType<T> type) {
        return plugin.getCore().getRegistryManager().createRegistry(type);
    }

    @Override
    public Set<RegistryType<?>> getRegistryTypes() {
        return plugin.getCore().getRegistryManager().getRegistryTypes();
    }

    @Override
    public <T> boolean hasRegistry(RegistryType<T> type) {
        return plugin.getCore().getRegistryManager().hasRegistry(type);
    }

    @Override
    public <T> Optional<T> getEntry(NamespacedKey key, Class<T> type) {
        return plugin.getCore().getRegistryManager().getEntry(key, type);
    }
}