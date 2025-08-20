package dev.voxelune.voxel.api.mechanics;

/**
 * Result of a mechanic execution.
 */
public interface MechanicResult {
    
    boolean isSuccess();
    String getErrorMessage();
    long getExecutionTime();
    int getTargetsAffected();
    
    static MechanicResult success(long executionTime, int targets) {
        return new MechanicResultImpl(true, null, executionTime, targets);
    }
    
    static MechanicResult failure(String error, long executionTime) {
        return new MechanicResultImpl(false, error, executionTime, 0);
    }
}