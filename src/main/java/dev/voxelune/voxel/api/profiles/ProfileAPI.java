package dev.voxelune.voxel.api.profiles;

import org.bukkit.entity.Player;

/**
 * API for player profiles and data management.
 */
public interface ProfileAPI {
    
    /**
     * Get a player's profile.
     */
    PlayerProfile getProfile(Player player);
    
    /**
     * Save a player's profile asynchronously.
     */
    void saveProfile(Player player);
    
    /**
     * Load a player's profile asynchronously.
     */
    void loadProfile(Player player);
}