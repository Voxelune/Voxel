package dev.voxelune.voxel.listeners;

import dev.voxelune.voxel.VoxelPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

/**
 * Main event listener for Voxel events.
 */
public class VoxelEventListener implements Listener {
    
    private final VoxelPlugin plugin;

    public VoxelEventListener(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        // This could be used to detect when dependent plugins are loaded
        if (plugin.getCore() != null && plugin.getCore().isInitialized()) {
            plugin.getVoxelLogger().debug("Plugin enabled: " + event.getPlugin().getName());
        }
    }
}