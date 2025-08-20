package dev.voxelune.voxel.api.jobs;

/**
 * Represents a job.
 */
public interface Job {
    
    String getId();
    String getDisplayName();
    int getLevel();
    double getExperience();
    double getExperienceToNext();
}