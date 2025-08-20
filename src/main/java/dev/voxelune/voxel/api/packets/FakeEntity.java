package dev.voxelune.voxel.api.packets;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 * Represents a fake entity that only exists client-side.
 */
public interface FakeEntity {
    
    int getEntityId();
    EntityType getType();
    Location getLocation();
    String getDisplayName();
    
    void setLocation(Location location);
    void setDisplayName(String name);
    void remove();
}