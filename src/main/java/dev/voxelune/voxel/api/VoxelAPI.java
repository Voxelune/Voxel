package dev.voxelune.voxel.api;

import dev.voxelune.voxel.api.registry.VoxelRegistry;
import dev.voxelune.voxel.api.mechanics.MechanicAPI;
import dev.voxelune.voxel.api.packets.PacketAPI;
import dev.voxelune.voxel.api.models.ModelAPI;
import dev.voxelune.voxel.api.stats.StatAPI;
import dev.voxelune.voxel.api.profiles.ProfileAPI;
import dev.voxelune.voxel.api.jobs.JobAPI;
import dev.voxelune.voxel.api.shops.ShopAPI;
import dev.voxelune.voxel.api.dungeons.DungeonAPI;
import dev.voxelune.voxel.api.worldgen.WorldgenAPI;
import dev.voxelune.voxel.api.scripting.ScriptingAPI;
import dev.voxelune.voxel.api.editor.EditorAPI;

/**
 * Root entry point for the Voxel Framework API.
 * 
 * This is the main interface that plugin developers will use to access
 * all Voxel Framework features. Access via Bukkit's Services Manager:
 * 
 * <pre>
 * VoxelAPI voxel = Bukkit.getServicesManager().getRegistration(VoxelAPI.class).getProvider();
 * </pre>
 */
public interface VoxelAPI {
    
    /**
     * Get a typed registry for the specified class.
     * 
     * @param <T> the type of objects stored in the registry
     * @param type the class of objects to register
     * @return a deferred registry for the specified type
     */
    <T> VoxelRegistry<T> registry(Class<T> type);
    
    /**
     * Get the Mechanic API for creating and executing mechanics.
     */
    MechanicAPI mechanics();
    
    /**
     * Get the Packet API for low-level packet manipulation.
     */
    PacketAPI packets();
    
    /**
     * Get the Model API for custom models and resourcepacks.
     */
    ModelAPI models();
    
    /**
     * Get the Stat API for player statistics and modifiers.
     */
    StatAPI stats();
    
    /**
     * Get the Profile API for player profiles and data.
     */
    ProfileAPI profiles();
    
    /**
     * Get the Job API for job systems and progression.
     */
    JobAPI jobs();
    
    /**
     * Get the Shop API for economy and trading.
     */
    ShopAPI shops();
    
    /**
     * Get the Dungeon API for procedural dungeons.
     */
    DungeonAPI dungeons();
    
    /**
     * Get the Worldgen API for custom world generation.
     */
    WorldgenAPI worldgen();
    
    /**
     * Get the Scripting API for embedded scripts.
     */
    ScriptingAPI scripting();
    
    /**
     * Get the Editor API for live editing integration.
     */
    EditorAPI editor();
    
    /**
     * Get the version of the Voxel Framework.
     */
    String getVersion();
    
    /**
     * Check if the framework is ready for use.
     */
    boolean isReady();
    
    /**
     * Check if developer mode is enabled.
     */
    boolean isDeveloperMode();
}