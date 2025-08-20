package dev.voxelune.voxel.api.scripting;

/**
 * Result of script execution.
 */
public interface ScriptResult {
    
    boolean isSuccess();
    Object getResult();
    String getError();
    long getExecutionTime();
}