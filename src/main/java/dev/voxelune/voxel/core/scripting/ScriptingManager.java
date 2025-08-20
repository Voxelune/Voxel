package dev.voxelune.voxel.core.scripting;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.scripting.*;

/**
 * Manages embedded scripting engines.
 */
public class ScriptingManager implements ScriptingAPI {
    
    private final VoxelPlugin plugin;

    public ScriptingManager(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing scripting manager...");
        plugin.getVoxelLogger().framework("Scripting manager initialized");
    }
    
    public void reload() {
        plugin.getVoxelLogger().framework("Scripting manager reloaded");
    }

    public void shutdown() {
        // Cleanup script engines
    }

    @Override
    public ScriptResult executeKotlin(String script) {
        plugin.getVoxelLogger().debug("Executing Kotlin script");
        return new ScriptResultImpl(true, "Mock result", null, 1);
    }

    @Override
    public ScriptResult executeJavaScript(String script) {
        plugin.getVoxelLogger().debug("Executing JavaScript script");
        return new ScriptResultImpl(true, "Mock result", null, 1);
    }

    @Override
    public boolean isScriptingEnabled() {
        return plugin.getConfiguration().isScriptingEnabled();
    }
}