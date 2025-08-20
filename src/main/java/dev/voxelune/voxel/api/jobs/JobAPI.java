package dev.voxelune.voxel.api.jobs;

import org.bukkit.entity.Player;

/**
 * API for job systems and progression.
 */
public interface JobAPI {
    
    /**
     * Get a player's current job.
     */
    Job getCurrentJob(Player player);
    
    /**
     * Set a player's job.
     */
    void setJob(Player player, String jobId);
    
    /**
     * Add experience to a player's job.
     */
    void addExperience(Player player, double experience);
}