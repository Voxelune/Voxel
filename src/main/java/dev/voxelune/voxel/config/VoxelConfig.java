package dev.voxelune.voxel.config;

import dev.voxelune.voxel.VoxelPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * Configuration handler for Voxel.
 */
public class VoxelConfig {
    
    private final VoxelPlugin plugin;
    private FileConfiguration config;

    public VoxelConfig(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        applyLogLevel();
    }

    public void reload() {
        plugin.reloadConfig();
        this.config = plugin.getConfig();
        applyLogLevel();
    }

    private void applyLogLevel() {
        String level = config.getString("logging.level", "INFO");
        plugin.getVoxelLogger().setLevel(level);
    }

    // Logging configuration
    public String getLogLevel() {
        return config.getString("logging.level", "INFO");
    }

    // Module configuration
    public boolean isModuleEnabled(String module) {
        return config.getBoolean("modules.enable." + module, true);
    }

    // Mechanics configuration
    public boolean isHotReloadEnabled() {
        return config.getBoolean("mechanics.hot_reload", true);
    }

    public List<String> getMechanicWatchDirs() {
        return config.getStringList("mechanics.watch_dirs");
    }

    // Registry configuration
    public boolean isAutoSaveEnabled() {
        return config.getBoolean("registries.auto_save", true);
    }

    public int getSaveInterval() {
        return config.getInt("registries.save_interval", 300);
    }

    // Capability configuration
    public boolean isStrictVersioning() {
        return config.getBoolean("capabilities.strict_versioning", false);
    }

    public boolean allowOverrides() {
        return config.getBoolean("capabilities.allow_overrides", false);
    }

    // Model router configuration
    public boolean isFallbackModelsEnabled() {
        return config.getBoolean("model_router.fallback_models", true);
    }

    public int getModelCacheSize() {
        return config.getInt("model_router.cache_size", 1000);
    }

    // Generic configuration getter
    public ConfigurationSection getSection(String path) {
        return config.getConfigurationSection(path);
    }
}