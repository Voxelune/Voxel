package dev.voxelune.voxel;

import dev.voxelune.voxel.api.VoxelAPI;
import dev.voxelune.voxel.command.VoxelCommand;
import dev.voxelune.voxel.config.VoxelConfig;
import dev.voxelune.voxel.core.VoxelFramework;
import dev.voxelune.voxel.core.VoxelLogger;
import dev.voxelune.voxel.impl.VoxelAPIImpl;
import dev.voxelune.voxel.listeners.VoxelEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Voxel Ultimate Plugin Developer Framework
 * 
 * A Forge/Fabric-like experience for Paper plugin development.
 * Provides registries, mechanics, packet API, scripting, worldgen,
 * and resourcepack pipelines for creating any type of plugin.
 */
public final class VoxelPlugin extends JavaPlugin {
    
    private static VoxelPlugin instance;
    private VoxelFramework framework;
    private VoxelConfig configuration;
    private VoxelAPIImpl api;
    private VoxelLogger logger;

    @Override
    public void onLoad() {
        instance = this;
        this.logger = new VoxelLogger(getLogger());
        
        logger.info("Loading Voxel Framework v" + getDescription().getVersion());
        logger.info("Ultimate Plugin Developer Framework - Forge/Fabric for Paper");
    }

    @Override
    public void onEnable() {
        logger.info("Enabling Voxel Framework...");
        
        // Initialize configuration
        saveDefaultConfig();
        this.configuration = new VoxelConfig(this);
        
        // Initialize core framework
        this.framework = new VoxelFramework(this);
        
        // Initialize API implementation
        this.api = new VoxelAPIImpl(this);
        
        // Register API service
        Bukkit.getServicesManager().register(VoxelAPI.class, api, this, ServicePriority.Normal);
        
        // Initialize framework modules
        if (!framework.initialize()) {
            logger.error("Failed to initialize Voxel Framework");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        // Register command
        getCommand("voxel").setExecutor(new VoxelCommand(this));
        
        // Register event listeners
        getServer().getPluginManager().registerEvents(new VoxelEventListener(this), this);
        
        // Create necessary directories
        createDirectories();
        
        logger.info("Voxel Framework enabled successfully!");
        logger.info("Developer mode: " + (configuration.isDeveloperMode() ? "ENABLED" : "DISABLED"));
        logger.info("Ready for plugin development!");
    }

    @Override
    public void onDisable() {
        if (framework != null) {
            framework.shutdown();
        }
        
        // Unregister service
        if (api != null) {
            Bukkit.getServicesManager().unregister(VoxelAPI.class, api);
        }
        
        logger.info("Voxel Framework disabled");
    }
    
    private void createDirectories() {
        // Create framework directories
        new File(getDataFolder(), "mechanics").mkdirs();
        new File(getDataFolder(), "definitions").mkdirs();
        new File(getDataFolder(), "scripts").mkdirs();
        new File(getDataFolder(), "modules").mkdirs();
        new File(getDataFolder(), "resourcepacks").mkdirs();
        new File(getDataFolder(), "examples").mkdirs();
        
        // Create example files
        createExampleFiles();
    }
    
    private void createExampleFiles() {
        // This would create example mechanics, items, etc.
        // Implementation would copy resources to the directories
    }
    
    public static VoxelPlugin getInstance() {
        return instance;
    }
    
    public VoxelFramework getFramework() {
        return framework;
    }
    
    public VoxelConfig getConfiguration() {
        return configuration;
    }
    
    public VoxelAPIImpl getApi() {
        return api;
    }
    
    public VoxelLogger getVoxelLogger() {
        return logger;
    }
    
    public void reload() {
        logger.info("Reloading Voxel Framework...");
        reloadConfig();
        configuration.reload();
        framework.reload();
        logger.info("Voxel Framework reloaded successfully!");
    }
}