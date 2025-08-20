package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.MechanicAPI;
import dev.voxelune.voxel.api.mechanic.Mechanic;
import dev.voxelune.voxel.api.mechanic.MechanicContext;
import dev.voxelune.voxel.api.mechanic.MechanicResult;
import org.bukkit.NamespacedKey;

import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Mechanic API.
 */
public class MechanicAPIImpl implements MechanicAPI {
    
    private final VoxelPlugin plugin;

    public MechanicAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Optional<Mechanic> loadMechanic(NamespacedKey key) {
        return plugin.getCore().getMechanicManager().loadMechanic(key);
    }

    @Override
    public Optional<Mechanic> getMechanic(NamespacedKey key) {
        return plugin.getCore().getMechanicManager().getMechanic(key);
    }

    @Override
    public MechanicResult executeMechanic(NamespacedKey key, MechanicContext context) {
        return plugin.getCore().getMechanicManager().executeMechanic(key, context);
    }

    @Override
    public MechanicResult executeMechanic(Mechanic mechanic, MechanicContext context) {
        return plugin.getCore().getMechanicManager().executeMechanic(mechanic, context);
    }

    @Override
    public Set<NamespacedKey> getLoadedMechanics() {
        return plugin.getCore().getMechanicManager().getLoadedMechanics();
    }

    @Override
    public void reloadMechanics() {
        plugin.getCore().getMechanicManager().reload();
    }

    @Override
    public boolean isHotReloadEnabled() {
        return plugin.getCore().getMechanicManager().isHotReloadEnabled();
    }
}