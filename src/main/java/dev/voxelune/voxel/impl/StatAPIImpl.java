package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.StatAPI;
import dev.voxelune.voxel.api.stat.Stat;
import dev.voxelune.voxel.api.stat.StatHolder;
import dev.voxelune.voxel.api.stat.StatModifier;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;

import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the Stat API.
 */
public class StatAPIImpl implements StatAPI {
    
    private final VoxelPlugin plugin;

    public StatAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void registerStat(Stat stat) {
        plugin.getCore().getStatManager().registerStat(stat);
    }

    @Override
    public Optional<Stat> getStat(NamespacedKey key) {
        return plugin.getCore().getStatManager().getStat(key);
    }

    @Override
    public StatHolder getStatHolder(Entity entity) {
        return plugin.getCore().getStatManager().getStatHolder(entity);
    }

    @Override
    public void applyModifier(Entity entity, StatModifier modifier) {
        plugin.getCore().getStatManager().applyModifier(entity, modifier);
    }

    @Override
    public void removeModifier(Entity entity, NamespacedKey modifierKey) {
        plugin.getCore().getStatManager().removeModifier(entity, modifierKey);
    }

    @Override
    public double getStatValue(Entity entity, NamespacedKey statKey) {
        return plugin.getCore().getStatManager().getStatValue(entity, statKey);
    }

    @Override
    public Set<NamespacedKey> getRegisteredStats() {
        return plugin.getCore().getStatManager().getRegisteredStats();
    }

    @Override
    public boolean hasStat(Entity entity, NamespacedKey statKey) {
        return plugin.getCore().getStatManager().hasStat(entity, statKey);
    }
}