package dev.voxelune.voxel.core.stat;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.stat.Stat;
import dev.voxelune.voxel.api.stat.StatHolder;
import dev.voxelune.voxel.api.stat.StatModifier;
import dev.voxelune.voxel.api.stat.StatType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages stat definitions and entity stat holders.
 */
public class StatManager {
    
    private final VoxelPlugin plugin;
    private final Map<NamespacedKey, Stat> stats;
    private final Map<Entity, StatHolder> statHolders;

    public StatManager(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.stats = new ConcurrentHashMap<>();
        this.statHolders = new ConcurrentHashMap<>();
    }

    public void initialize() {
        plugin.getVoxelLogger().info("Initializing stat manager...");
        
        // Register default stats
        registerDefaultStats();
        
        plugin.getVoxelLogger().info("Stat manager initialized with " + stats.size() + " stats");
    }

    public void reload() {
        // Stat reload logic would go here
        plugin.getVoxelLogger().info("Stat manager reloaded");
    }

    public void shutdown() {
        statHolders.clear();
        stats.clear();
    }

    private void registerDefaultStats() {
        // Intelligence stat used in example mechanic
        Stat intelligence = Stat.builder(new NamespacedKey("voxel", "intelligence"))
            .displayName("Intelligence")
            .type(StatType.ATTRIBUTE)
            .defaultValue(10.0)
            .minValue(0.0)
            .maxValue(100.0)
            .description("Mental acuity and magical power")
            .build();
        registerStat(intelligence);
        
        // Health stat
        Stat health = Stat.builder(new NamespacedKey("voxel", "health"))
            .displayName("Health")
            .type(StatType.RESOURCE)
            .defaultValue(100.0)
            .minValue(0.0)
            .maxValue(1000.0)
            .description("Life force")
            .build();
        registerStat(health);
    }

    public void registerStat(Stat stat) {
        stats.put(stat.key(), stat);
        plugin.getVoxelLogger().debug("Registered stat: " + stat.key());
    }

    public Optional<Stat> getStat(NamespacedKey key) {
        return Optional.ofNullable(stats.get(key));
    }

    public StatHolder getStatHolder(Entity entity) {
        return statHolders.computeIfAbsent(entity, e -> new VoxelStatHolder(this));
    }

    public void applyModifier(Entity entity, StatModifier modifier) {
        StatHolder holder = getStatHolder(entity);
        // Extract stat key from modifier - this would need to be passed or stored
        // For now, we'll use a placeholder approach
        plugin.getVoxelLogger().debug("Applied modifier " + modifier.key() + " to entity " + entity.getUniqueId());
    }

    public void removeModifier(Entity entity, NamespacedKey modifierKey) {
        StatHolder holder = statHolders.get(entity);
        if (holder != null) {
            // Remove modifier from all stats - in a real implementation we'd track which stat it belongs to
            plugin.getVoxelLogger().debug("Removed modifier " + modifierKey + " from entity " + entity.getUniqueId());
        }
    }

    public double getStatValue(Entity entity, NamespacedKey statKey) {
        StatHolder holder = statHolders.get(entity);
        if (holder != null && holder.hasStat(statKey)) {
            return holder.getFinalValue(statKey);
        }
        
        // Return default value if stat exists but entity doesn't have it
        return getStat(statKey).map(Stat::defaultValue).orElse(0.0);
    }

    public Set<NamespacedKey> getRegisteredStats() {
        return Set.copyOf(stats.keySet());
    }

    public boolean hasStat(Entity entity, NamespacedKey statKey) {
        return stats.containsKey(statKey);
    }

    // Package-private getter for StatHolder implementation
    Map<NamespacedKey, Stat> getStats() {
        return stats;
    }
}