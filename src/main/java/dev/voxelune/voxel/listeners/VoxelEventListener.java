package dev.voxelune.voxel.listeners;

import dev.voxelune.voxel.VoxelPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Main event listener for the Voxel Framework.
 */
public class VoxelEventListener implements Listener {
    
    private final VoxelPlugin plugin;

    public VoxelEventListener(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load player profile
        plugin.getFramework().getProfileManager().loadProfile(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Save player profile
        plugin.getFramework().getProfileManager().saveProfile(event.getPlayer());
    }
}