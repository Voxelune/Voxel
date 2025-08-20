package dev.voxelune.voxel.core.editor;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.editor.*;

/**
 * Manages editor integration and live editing.
 */
public class EditorManager implements EditorAPI {
    
    private final VoxelPlugin plugin;

    public EditorManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing editor manager...");
        plugin.getVoxelLogger().framework("Editor manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Editor manager reloaded");
    }

    public void shutdown() {
        // Cleanup editor connections
    }

    @Override
    public boolean isEditorEnabled() {
        return plugin.getConfiguration().isEditorEnabled();
    }

    @Override
    public boolean isLiveEditingEnabled() {
        return plugin.getConfiguration().isLiveEditing();
    }

    @Override
    public void hotReload() {
        plugin.getVoxelLogger().framework("Performing hot reload...");
        plugin.reload();
    }
}