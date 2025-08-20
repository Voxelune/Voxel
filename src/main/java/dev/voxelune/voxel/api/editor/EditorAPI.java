package dev.voxelune.voxel.api.editor;

/**
 * API for editor integration and live editing.
 */
public interface EditorAPI {
    
    /**
     * Check if editor integration is enabled.
     */
    boolean isEditorEnabled();
    
    /**
     * Check if live editing is enabled.
     */
    boolean isLiveEditingEnabled();
    
    /**
     * Trigger a hot reload of definitions.
     */
    void hotReload();
}