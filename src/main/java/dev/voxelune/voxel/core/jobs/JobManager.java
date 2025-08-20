package dev.voxelune.voxel.core.jobs;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.jobs.*;
import org.bukkit.entity.Player;

/**
 * Manages job systems and progression.
 */
public class JobManager implements JobAPI {
    
    private final VoxelPlugin plugin;

    public JobManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing job manager...");
        plugin.getVoxelLogger().framework("Job manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Job manager reloaded");
    }

    public void shutdown() {
        // Cleanup
    }

    @Override
    public Job getCurrentJob(Player player) {
        return new JobImpl("miner", "Miner", 1, 0, 100);
    }

    @Override
    public void setJob(Player player, String jobId) {
        plugin.getVoxelLogger().debug("Set job " + jobId + " for " + player.getName());
    }

    @Override
    public void addExperience(Player player, double experience) {
        plugin.getVoxelLogger().debug("Added " + experience + " XP to " + player.getName());
    }
}