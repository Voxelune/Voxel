package dev.voxelune.voxel.core;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.core.capability.CapabilityManager;
import dev.voxelune.voxel.core.mechanic.MechanicManager;
import dev.voxelune.voxel.core.model.ModelManager;
import dev.voxelune.voxel.core.registry.RegistryManager;
import dev.voxelune.voxel.core.stat.StatManager;

/**
 * Core system that manages all Voxel modules and their lifecycle.
 */
public class VoxelCore {
    
    private final VoxelPlugin plugin;
    private final VoxelLogger logger;
    
    private RegistryManager registryManager;
    private MechanicManager mechanicManager;
    private CapabilityManager capabilityManager;
    private ModelManager modelManager;
    private StatManager statManager;
    
    private boolean initialized = false;

    public VoxelCore(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getVoxelLogger();
    }

    public boolean initialize() {
        logger.info("Initializing Voxel core modules...");
        
        try {
            // Initialize managers in dependency order
            this.registryManager = new RegistryManager(plugin);
            this.capabilityManager = new CapabilityManager(plugin);
            this.statManager = new StatManager(plugin);
            this.modelManager = new ModelManager(plugin);
            this.mechanicManager = new MechanicManager(plugin);
            
            // Initialize each module
            registryManager.initialize();
            capabilityManager.initialize();
            statManager.initialize();
            modelManager.initialize();
            mechanicManager.initialize();
            
            this.initialized = true;
            logger.info("Core modules initialized successfully");
            return true;
            
        } catch (Exception e) {
            logger.error("Failed to initialize core modules: " + e.getMessage(), e);
            return false;
        }
    }

    public void reload() {
        if (!initialized) return;
        
        logger.info("Reloading core modules...");
        
        try {
            mechanicManager.reload();
            modelManager.reload();
            statManager.reload();
            
            logger.info("Core modules reloaded successfully");
        } catch (Exception e) {
            logger.error("Failed to reload core modules: " + e.getMessage(), e);
        }
    }

    public void shutdown() {
        if (!initialized) return;
        
        logger.info("Shutting down core modules...");
        
        try {
            if (mechanicManager != null) mechanicManager.shutdown();
            if (modelManager != null) modelManager.shutdown();
            if (statManager != null) statManager.shutdown();
            if (capabilityManager != null) capabilityManager.shutdown();
            if (registryManager != null) registryManager.shutdown();
            
            this.initialized = false;
            logger.info("Core modules shut down successfully");
        } catch (Exception e) {
            logger.error("Error during shutdown: " + e.getMessage(), e);
        }
    }

    // Getters
    public RegistryManager getRegistryManager() { return registryManager; }
    public MechanicManager getMechanicManager() { return mechanicManager; }
    public CapabilityManager getCapabilityManager() { return capabilityManager; }
    public ModelManager getModelManager() { return modelManager; }
    public StatManager getStatManager() { return statManager; }
    
    public boolean isInitialized() { return initialized; }
}