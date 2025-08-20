package dev.voxelune.voxel.api.mechanics;

record MechanicResultImpl(boolean success, String errorMessage, long executionTime, int targetsAffected) implements MechanicResult {
    @Override
    public boolean isSuccess() { return success; }
    @Override
    public String getErrorMessage() { return errorMessage; }
    @Override
    public long getExecutionTime() { return executionTime; }
    @Override
    public int getTargetsAffected() { return targetsAffected; }
}