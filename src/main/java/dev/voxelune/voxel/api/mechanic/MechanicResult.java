package dev.voxelune.voxel.api.mechanic;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Result of a mechanic execution.
 */
public class MechanicResult {
    
    private final boolean success;
    private final String errorMessage;
    private final List<Object> targetsAffected;
    private final long executionTime;
    private final Instant timestamp;

    private MechanicResult(boolean success, String errorMessage, List<Object> targetsAffected, long executionTime) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.targetsAffected = new ArrayList<>(targetsAffected);
        this.executionTime = executionTime;
        this.timestamp = Instant.now();
    }

    public boolean isSuccess() { return success; }
    public Optional<String> getErrorMessage() { return Optional.ofNullable(errorMessage); }
    public List<Object> getTargetsAffected() { return new ArrayList<>(targetsAffected); }
    public int getTargetsCount() { return targetsAffected.size(); }
    public long getExecutionTime() { return executionTime; }
    public Instant getTimestamp() { return timestamp; }

    public static MechanicResult success(List<Object> targets, long executionTime) {
        return new MechanicResult(true, null, targets, executionTime);
    }

    public static MechanicResult success(long executionTime) {
        return new MechanicResult(true, null, List.of(), executionTime);
    }

    public static MechanicResult failure(String errorMessage, long executionTime) {
        return new MechanicResult(false, errorMessage, List.of(), executionTime);
    }
}