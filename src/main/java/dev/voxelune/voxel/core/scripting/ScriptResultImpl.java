package dev.voxelune.voxel.core.scripting;

import dev.voxelune.voxel.api.scripting.ScriptResult;

record ScriptResultImpl(boolean success, Object result, String error, long executionTime) implements ScriptResult {
    @Override
    public boolean isSuccess() { return success; }
    @Override
    public Object getResult() { return result; }
    @Override
    public String getError() { return error; }
    @Override
    public long getExecutionTime() { return executionTime; }
}