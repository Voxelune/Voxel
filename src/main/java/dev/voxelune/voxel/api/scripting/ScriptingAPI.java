package dev.voxelune.voxel.api.scripting;

/**
 * API for embedded scripting engines.
 */
public interface ScriptingAPI {
    
    /**
     * Execute a Kotlin script.
     */
    ScriptResult executeKotlin(String script);
    
    /**
     * Execute a JavaScript script.
     */
    ScriptResult executeJavaScript(String script);
    
    /**
     * Check if scripting is enabled.
     */
    boolean isScriptingEnabled();
}