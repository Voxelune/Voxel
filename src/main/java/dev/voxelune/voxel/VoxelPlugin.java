package dev.voxelune.voxel;

import dev.voxelune.voxel.api.VoxelAPI;
import dev.voxelune.voxel.command.VoxelCommand;
import dev.voxelune.voxel.config.VoxelConfig;
import dev.voxelune.voxel.core.VoxelCore;
import dev.voxelune.voxel.core.VoxelLogger;
import dev.voxelune.voxel.impl.VoxelAPIImpl;
import dev.voxelune.voxel.listeners.VoxelEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class VoxelPlugin extends JavaPlugin {
    
    private static VoxelPlugin instance;
    private VoxelCore core;
    private VoxelConfig configuration;
    private VoxelAPIImpl api;
    private VoxelLogger logger;

    @Override
    public void onLoad() {
        instance = this;
        this.logger = new VoxelLogger(getLogger());
    }

    @Override
    public void onEnable() {
        logger.info("Starting Voxel v" + getDescription().getVersion());
        
        // Initialize configuration
        saveDefaultConfig();
        this.configuration = new VoxelConfig(this);
        
        // Initialize core system
        this.core = new VoxelCore(this);
        
        // Initialize API implementation
        this.api = new VoxelAPIImpl(this);
        
        // Register API service
        Bukkit.getServicesManager().register(VoxelAPI.class, api, this, ServicePriority.Normal);
        
        // Initialize core modules
        if (!core.initialize()) {
            logger.error("Failed to initialize core modules");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        // Register command
        getCommand("voxel").setExecutor(new VoxelCommand(this));
        
        // Register event listeners
        getServer().getPluginManager().registerEvents(new VoxelEventListener(this), this);
        
        // Create mechanics directory
        File mechanicsDir = new File(getDataFolder(), "mechanics");
        if (!mechanicsDir.exists()) {
            mechanicsDir.mkdirs();
            // Create example mechanic
            createExampleMechanic(mechanicsDir);
        }
        
        logger.info("Voxel enabled successfully!");
    }

    @Override
    public void onDisable() {
        if (core != null) {
            core.shutdown();
        }
        
        // Unregister service
        Bukkit.getServicesManager().unregister(VoxelAPI.class, api);
        
        logger.info("Voxel disabled");
    }
    
    private void createExampleMechanic(File mechanicsDir) {
        File exampleFile = new File(mechanicsDir, "example_firebolt.yml");
        if (!exampleFile.exists()) {
            try {
                saveResource("mechanics/example_firebolt.yml", false);
            } catch (Exception e) {
                logger.warning("Could not create example mechanic: " + e.getMessage());
            }
        }
    }
    
    public static VoxelPlugin getInstance() {
        return instance;
    }
    
    public VoxelCore getCore() {
        return core;
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
        reloadConfig();
        configuration.reload();
        core.reload();
        logger.info("Voxel reloaded successfully!");
    }
}