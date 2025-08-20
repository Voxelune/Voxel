package dev.voxelune.voxel.impl;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.VoxelAPI;
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
 * Main implementation of the Voxel Framework API.
 */
public class VoxelAPIImpl implements VoxelAPI {
    
    private final VoxelPlugin plugin;

    public VoxelAPIImpl(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public <T> VoxelRegistry<T> registry(Class<T> type) {
        return plugin.getFramework().getRegistryManager().getRegistry(type);
    }

    @Override
    public MechanicAPI mechanics() {
        return plugin.getFramework().getMechanicManager();
    }

    @Override
    public PacketAPI packets() {
        return plugin.getFramework().getPacketManager();
    }

    @Override
    public ModelAPI models() {
        return plugin.getFramework().getModelManager();
    }

    @Override
    public StatAPI stats() {
        return plugin.getFramework().getStatManager();
    }

    @Override
    public ProfileAPI profiles() {
        return plugin.getFramework().getProfileManager();
    }

    @Override
    public JobAPI jobs() {
        return plugin.getFramework().getJobManager();
    }

    @Override
    public ShopAPI shops() {
        return plugin.getFramework().getShopManager();
    }

    @Override
    public DungeonAPI dungeons() {
        return plugin.getFramework().getDungeonManager();
    }

    @Override
    public WorldgenAPI worldgen() {
        return plugin.getFramework().getWorldgenManager();
    }

    @Override
    public ScriptingAPI scripting() {
        return plugin.getFramework().getScriptingManager();
    }

    @Override
    public EditorAPI editor() {
        return plugin.getFramework().getEditorManager();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean isReady() {
        return plugin.getFramework() != null && plugin.getFramework().isInitialized();
    }

    @Override
    public boolean isDeveloperMode() {
        return plugin.getConfiguration().isDeveloperMode();
    }
}