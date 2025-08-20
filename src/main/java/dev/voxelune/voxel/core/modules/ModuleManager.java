package dev.voxelune.voxel.core.modules;

import dev.voxelune.voxel.VoxelPlugin;

/**
 * Manages external modules loaded from plugins/Voxel/modules/
 */
public class ModuleManager {
    
    private final VoxelPlugin plugin;

    public ModuleManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing module manager...");
        
        if (plugin.getConfiguration().isModuleLoadingEnabled()) {
            plugin.getVoxelLogger().warning("Module loading is enabled - this is a security risk!");
            // Load external modules from modules directory
        } else {
            plugin.getVoxelLogger().framework("Module loading is disabled (recommended for production)");
        }
        
        plugin.getVoxelLogger().framework("Module manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Module manager reloaded");
    }

    public void shutdown() {
        // Unload modules
    }
}