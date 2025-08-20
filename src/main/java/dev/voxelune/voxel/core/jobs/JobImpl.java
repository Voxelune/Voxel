package dev.voxelune.voxel.core.jobs;

import dev.voxelune.voxel.api.jobs.Job;

record JobImpl(String id, String displayName, int level, double experience, double experienceToNext) implements Job {
    @Override
    public String getId() { return id; }
    @Override
    public String getDisplayName() { return displayName; }
    @Override
    public int getLevel() { return level; }
    @Override
    public double getExperience() { return experience; }
    @Override
    public double getExperienceToNext() { return experienceToNext; }
}