package dev.voxelune.voxel.core.profiles;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.profiles.*;
import org.bukkit.entity.Player;

/**
 * Manages player profiles and data persistence.
 */
public class ProfileManager implements ProfileAPI {
    
    private final VoxelPlugin plugin;

    public ProfileManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing profile manager...");
        plugin.getVoxelLogger().framework("Profile manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Profile manager reloaded");
    }

    public void shutdown() {
        // Save all profiles
    }

    @Override
    public PlayerProfile getProfile(Player player) {
        return new PlayerProfileImpl(player.getUniqueId());
    }

    @Override
    public void saveProfile(Player player) {
        plugin.getVoxelLogger().debug("Saving profile for " + player.getName());
    }

    @Override
    public void loadProfile(Player player) {
        plugin.getVoxelLogger().debug("Loading profile for " + player.getName());
    }
}