package dev.voxelune.voxel.config;

import dev.voxelune.voxel.VoxelPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Configuration handler for the Voxel Framework.
 * 
 * Manages all framework settings with sane defaults and developer-friendly
 * configuration options.
 */
public class VoxelConfig {
    
    private final VoxelPlugin plugin;
    private FileConfiguration config;

    public VoxelConfig(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        applySettings();
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
        applySettings();
    }

    private void applySettings() {
        // Apply developer mode settings
        boolean devMode = isDeveloperMode();
        plugin.getVoxelLogger().setDeveloperMode(devMode);
        
        if (isDebugLogging()) {
            plugin.getVoxelLogger().setLevel("FINE");
        }
    }

    // Developer settings
    public boolean isDeveloperMode() {
        return config.getBoolean("developer.mode", true);
    }
    
    public boolean isHotReloadEnabled() {
        return config.getBoolean("developer.hot_reload", true);
    }
    
    public boolean isDebugLogging() {
        return config.getBoolean("developer.debug_logging", true);
    }
    
    public boolean isModuleLoadingEnabled() {
        return config.getBoolean("developer.module_loading", false);
    }

    // Module settings
    public boolean isModuleEnabled(String module) {
        return config.getBoolean("modules." + module, true);
    }

    // Performance settings
    public boolean isAsyncIOEnabled() {
        return config.getBoolean("performance.async_io", true);
    }
    
    public int getRegistryCacheSize() {
        return config.getInt("performance.registry_cache_size", 10000);
    }
    
    public long getMechanicExecutionTimeout() {
        return config.getLong("performance.mechanic_execution_timeout", 5000);
    }
    
    public long getScriptExecutionTimeout() {
        return config.getLong("performance.script_execution_timeout", 1000);
    }
    
    public int getMaxConcurrentMechanics() {
        return config.getInt("performance.max_concurrent_mechanics", 100);
    }

    // Scripting settings
    public boolean isScriptingEnabled() {
        return config.getBoolean("scripting.enabled", true);
    }
    
    public boolean isKotlinEnabled() {
        return config.getBoolean("scripting.languages.kotlin", true);
    }
    
    public boolean isJavaScriptEnabled() {
        return config.getBoolean("scripting.languages.javascript", true);
    }
    
    public boolean isSandboxMode() {
        return config.getBoolean("scripting.security.sandbox_mode", true);
    }
    
    public boolean isWhitelistAPIs() {
        return config.getBoolean("scripting.security.whitelist_apis", true);
    }
    
    public int getMaxScriptMemoryMB() {
        return config.getInt("scripting.security.max_memory_mb", 64);
    }

    // Packet API settings
    public boolean isPacketAPIEnabled() {
        return config.getBoolean("packets.enabled", true);
    }
    
    public boolean isInterceptChat() {
        return config.getBoolean("packets.intercept_chat", true);
    }
    
    public boolean isInterceptInventory() {
        return config.getBoolean("packets.intercept_inventory", true);
    }
    
    public boolean isFakeEntitiesEnabled() {
        return config.getBoolean("packets.fake_entities", true);
    }
    
    public boolean isCustomGUIsEnabled() {
        return config.getBoolean("packets.custom_guis", true);
    }

    // Resource pack settings
    public boolean isResourcePacksEnabled() {
        return config.getBoolean("resourcepacks.enabled", true);
    }
    
    public boolean isAutoGeneration() {
        return config.getBoolean("resourcepacks.auto_generation", true);
    }
    
    public boolean isMergeExisting() {
        return config.getBoolean("resourcepacks.merge_existing", true);
    }
    
    public boolean isBedrockFallbacks() {
        return config.getBoolean("resourcepacks.bedrock_fallbacks", true);
    }
    
    public String getResourcePackOutputDirectory() {
        return config.getString("resourcepacks.output_directory", "generated");
    }

    // Persistence settings
    public boolean isAsyncSaves() {
        return config.getBoolean("persistence.async_saves", true);
    }
    
    public int getSaveInterval() {
        return config.getInt("persistence.save_interval", 300);
    }
    
    public boolean isBackupProfiles() {
        return config.getBoolean("persistence.backup_profiles", true);
    }
    
    public boolean isCompressionEnabled() {
        return config.getBoolean("persistence.compression", true);
    }

    // Editor integration settings
    public boolean isEditorEnabled() {
        return config.getBoolean("editor.enabled", true);
    }
    
    public boolean isLiveEditing() {
        return config.getBoolean("editor.live_editing", true);
    }
    
    public boolean isEditorDebugMode() {
        return config.getBoolean("editor.debug_mode", true);
    }

    // Generic configuration getter
    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }
    
    public FileConfiguration getConfig() {
        return config;
    }
}