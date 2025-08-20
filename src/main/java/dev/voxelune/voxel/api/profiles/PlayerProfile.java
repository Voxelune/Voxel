package dev.voxelune.voxel.api.profiles;

import java.util.Map;
import java.util.UUID;

/**
 * Represents a player's profile data.
 */
public interface PlayerProfile {
    
    UUID getPlayerId();
    Map<String, Object> getData();
    
    void setData(String key, Object value);
    <T> T getData(String key, Class<T> type);
    
    boolean isDirty();
    void markClean();
}