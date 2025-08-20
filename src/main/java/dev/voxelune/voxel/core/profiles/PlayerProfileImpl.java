package dev.voxelune.voxel.core.profiles;

import dev.voxelune.voxel.api.profiles.PlayerProfile;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of PlayerProfile.
 */
public class PlayerProfileImpl implements PlayerProfile {
    
    private final UUID playerId;
    private final Map<String, Object> data = new ConcurrentHashMap<>();
    private boolean dirty = false;

    public PlayerProfileImpl(UUID playerId) {
        this.playerId = playerId;
    }

    @Override
    public UUID getPlayerId() {
        return playerId;
    }

    @Override
    public Map<String, Object> getData() {
        return Map.copyOf(data);
    }

    @Override
    public void setData(String key, Object value) {
        data.put(key, value);
        dirty = true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getData(String key, Class<T> type) {
        Object value = data.get(key);
        return type.isInstance(value) ? (T) value : null;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void markClean() {
        dirty = false;
    }
}