package dev.voxelune.voxel.core.registry;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.registry.VoxelRegistry;
import dev.voxelune.voxel.api.definitions.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages all registries in the Voxel Framework.
 * 
 * This is the central registry manager that coordinates all the different
 * types of registries and ensures they work together properly.
 */
public class RegistryManager {
    
    private final VoxelPlugin plugin;
    private final Map<Class<?>, VoxelRegistry<?>> registries;
    private boolean frozen = false;

    public RegistryManager(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.registries = new ConcurrentHashMap<>();
    }

    public void initialize() {
        plugin.getVoxelLogger().framework("Initializing registry manager...");
        
        // Create default registries for common types
        createRegistry(ItemDef.class);
        createRegistry(BlockDef.class);
        createRegistry(MobDef.class);
        createRegistry(NpcDef.class);
        createRegistry(EnchantDef.class);
        createRegistry(JobDef.class);
        createRegistry(TeamDef.class);
        createRegistry(LevelDef.class);
        createRegistry(ShopDef.class);
        createRegistry(DungeonDef.class);
        createRegistry(WorldgenDef.class);
        createRegistry(CosmeticDef.class);
        createRegistry(CrateDef.class);
        createRegistry(StatDef.class);
        createRegistry(ElementDef.class);
        createRegistry(MechanicDef.class);
        
        plugin.getVoxelLogger().framework("Registry manager initialized with " + registries.size() + " registries");
    }
    
    public void reload() {
        // Registry reload logic - careful not to break existing references
        plugin.getVoxelLogger().framework("Registry manager reloaded");
    }

    public void shutdown() {
        // Freeze all registries
        registries.values().forEach(VoxelRegistry::freeze);
        frozen = true;
        plugin.getVoxelLogger().framework("Registry manager shut down");
    }

    @SuppressWarnings("unchecked")
    public <T> VoxelRegistry<T> getRegistry(Class<T> type) {
        return (VoxelRegistry<T>) registries.computeIfAbsent(type, this::createRegistryInternal);
    }
    
    @SuppressWarnings("unchecked")
    private <T> VoxelRegistry<T> createRegistry(Class<T> type) {
        if (frozen) {
            throw new IllegalStateException("Cannot create registry after framework shutdown");
        }
        
        VoxelRegistry<T> registry = new VoxelRegistryImpl<>(type, plugin);
        registries.put(type, registry);
        
        plugin.getVoxelLogger().debug("Created registry for type: " + type.getSimpleName());
        return registry;
    }
    
    @SuppressWarnings("unchecked")
    private <T> VoxelRegistry<T> createRegistryInternal(Class<?> type) {
        return createRegistry((Class<T>) type);
    }
    
    public Map<Class<?>, VoxelRegistry<?>> getAllRegistries() {
        return Map.copyOf(registries);
    }
    
    public boolean isFrozen() {
        return frozen;
    }
}