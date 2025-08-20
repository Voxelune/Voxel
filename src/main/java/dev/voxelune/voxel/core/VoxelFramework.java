package dev.voxelune.voxel.core;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.core.registry.RegistryManager;
import dev.voxelune.voxel.core.mechanics.MechanicManager;
import dev.voxelune.voxel.core.packets.PacketManager;
import dev.voxelune.voxel.core.models.ModelManager;
import dev.voxelune.voxel.core.stats.StatManager;
import dev.voxelune.voxel.core.profiles.ProfileManager;
import dev.voxelune.voxel.core.jobs.JobManager;
import dev.voxelune.voxel.core.shops.ShopManager;
import dev.voxelune.voxel.core.dungeons.DungeonManager;
import dev.voxelune.voxel.core.worldgen.WorldgenManager;
import dev.voxelune.voxel.core.scripting.ScriptingManager;
import dev.voxelune.voxel.core.editor.EditorManager;
import dev.voxelune.voxel.core.modules.ModuleManager;

/**
 * Core framework that manages all Voxel systems and their lifecycle.
 * 
 * This is the heart of the Voxel Framework, coordinating all the different
 * systems and ensuring they work together seamlessly.
 */
public class VoxelFramework {
    
    private final VoxelPlugin plugin;
    private final VoxelLogger logger;
    
    // Core managers
    private RegistryManager registryManager;
    private MechanicManager mechanicManager;
    private PacketManager packetManager;
    private ModelManager modelManager;
    private StatManager statManager;
    private ProfileManager profileManager;
    private JobManager jobManager;
    private ShopManager shopManager;
    private DungeonManager dungeonManager;
    private WorldgenManager worldgenManager;
    private ScriptingManager scriptingManager;
    private EditorManager editorManager;
    private ModuleManager moduleManager;
    
    private boolean initialized = false;

    public VoxelFramework(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getVoxelLogger();
    }

    public boolean initialize() {
        logger.info("Initializing Voxel Framework modules...");
        
        try {
            // Initialize managers in dependency order
            this.registryManager = new RegistryManager(plugin);
            this.packetManager = new PacketManager(plugin);
            this.scriptingManager = new ScriptingManager(plugin);
            this.statManager = new StatManager(plugin);
            this.profileManager = new ProfileManager(plugin);
            this.modelManager = new ModelManager(plugin);
            this.mechanicManager = new MechanicManager(plugin);
            this.jobManager = new JobManager(plugin);
            this.shopManager = new ShopManager(plugin);
            this.dungeonManager = new DungeonManager(plugin);
            this.worldgenManager = new WorldgenManager(plugin);
            this.editorManager = new EditorManager(plugin);
            this.moduleManager = new ModuleManager(plugin);
            
            // Initialize each module
            registryManager.initialize();
            packetManager.initialize();
            scriptingManager.initialize();
            statManager.initialize();
            profileManager.initialize();
            modelManager.initialize();
            mechanicManager.initialize();
            jobManager.initialize();
            shopManager.initialize();
            dungeonManager.initialize();
            worldgenManager.initialize();
            editorManager.initialize();
            moduleManager.initialize();
            
            this.initialized = true;
            logger.info("Framework modules initialized successfully");
            return true;
            
        } catch (Exception e) {
            logger.error("Failed to initialize framework modules: " + e.getMessage(), e);
            return false;
        }
    }

    public void reload() {
        if (!initialized) return;
        
        logger.info("Reloading framework modules...");
        
        try {
            // Reload in reverse dependency order
            editorManager.reload();
            moduleManager.reload();
            worldgenManager.reload();
            dungeonManager.reload();
            shopManager.reload();
            jobManager.reload();
            mechanicManager.reload();
            modelManager.reload();
            profileManager.reload();
            statManager.reload();
            scriptingManager.reload();
            packetManager.reload();
            registryManager.reload();
            
            logger.info("Framework modules reloaded successfully");
        } catch (Exception e) {
            logger.error("Failed to reload framework modules: " + e.getMessage(), e);
        }
    }

    public void shutdown() {
        if (!initialized) return;
        
        logger.info("Shutting down framework modules...");
        
        try {
            // Shutdown in reverse order
            if (moduleManager != null) moduleManager.shutdown();
            if (editorManager != null) editorManager.shutdown();
            if (worldgenManager != null) worldgenManager.shutdown();
            if (dungeonManager != null) dungeonManager.shutdown();
            if (shopManager != null) shopManager.shutdown();
            if (jobManager != null) jobManager.shutdown();
            if (mechanicManager != null) mechanicManager.shutdown();
            if (modelManager != null) modelManager.shutdown();
            if (profileManager != null) profileManager.shutdown();
            if (statManager != null) statManager.shutdown();
            if (scriptingManager != null) scriptingManager.shutdown();
            if (packetManager != null) packetManager.shutdown();
            if (registryManager != null) registryManager.shutdown();
            
            this.initialized = false;
            logger.info("Framework modules shut down successfully");
        } catch (Exception e) {
            logger.error("Error during shutdown: " + e.getMessage(), e);
        }
    }

    // Getters for all managers
    public RegistryManager getRegistryManager() { return registryManager; }
    public MechanicManager getMechanicManager() { return mechanicManager; }
    public PacketManager getPacketManager() { return packetManager; }
    public ModelManager getModelManager() { return modelManager; }
    public StatManager getStatManager() { return statManager; }
    public ProfileManager getProfileManager() { return profileManager; }
    public JobManager getJobManager() { return jobManager; }
    public ShopManager getShopManager() { return shopManager; }
    public DungeonManager getDungeonManager() { return dungeonManager; }
    public WorldgenManager getWorldgenManager() { return worldgenManager; }
    public ScriptingManager getScriptingManager() { return scriptingManager; }
    public EditorManager getEditorManager() { return editorManager; }
    public ModuleManager getModuleManager() { return moduleManager; }
    
    public boolean isInitialized() { return initialized; }
}