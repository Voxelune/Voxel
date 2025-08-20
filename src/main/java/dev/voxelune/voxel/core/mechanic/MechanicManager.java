package dev.voxelune.voxel.core.mechanic;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.mechanic.Mechanic;
import dev.voxelune.voxel.api.mechanic.MechanicContext;
import dev.voxelune.voxel.api.mechanic.MechanicResult;
import dev.voxelune.voxel.core.mechanic.parser.MechanicParser;
import org.bukkit.NamespacedKey;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages mechanic loading, compilation, and execution.
 */
public class MechanicManager {
    
    private final VoxelPlugin plugin;
    private final Map<NamespacedKey, Mechanic> mechanics;
    private final MechanicParser parser;

    public MechanicManager(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.mechanics = new ConcurrentHashMap<>();
        this.parser = new MechanicParser(plugin);
    }

    public void initialize() {
        plugin.getVoxelLogger().info("Initializing mechanic manager...");
        
        // Load mechanics from configured directories
        var config = plugin.getConfiguration();
        var watchDirs = config.getMechanicWatchDirs();
        
        for (String dirPath : watchDirs) {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
                plugin.getVoxelLogger().debug("Created mechanic directory: " + dirPath);
            }
            loadMechanicsFromDirectory(dir);
        }
        
        plugin.getVoxelLogger().info("Loaded " + mechanics.size() + " mechanics");
    }

    public void reload() {
        mechanics.clear();
        initialize();
    }

    public void shutdown() {
        mechanics.clear();
    }

    private void loadMechanicsFromDirectory(File directory) {
        if (!directory.isDirectory()) return;
        
        File[] files = directory.listFiles((dir, name) -> 
            name.endsWith(".yml") || name.endsWith(".yaml"));
            
        if (files == null) return;
        
        for (File file : files) {
            try {
                Optional<Mechanic> mechanic = parser.parseFromFile(file);
                if (mechanic.isPresent()) {
                    mechanics.put(mechanic.get().getId(), mechanic.get());
                    plugin.getVoxelLogger().debug("Loaded mechanic: " + mechanic.get().getId());
                } else {
                    plugin.getVoxelLogger().warning("Failed to parse mechanic file: " + file.getName());
                }
            } catch (Exception e) {
                plugin.getVoxelLogger().error("Error loading mechanic from " + file.getName() + ": " + e.getMessage(), e);
            }
        }
    }

    public Optional<Mechanic> loadMechanic(NamespacedKey key) {
        return Optional.ofNullable(mechanics.get(key));
    }

    public Optional<Mechanic> getMechanic(NamespacedKey key) {
        return Optional.ofNullable(mechanics.get(key));
    }

    public MechanicResult executeMechanic(NamespacedKey key, MechanicContext context) {
        Optional<Mechanic> mechanic = getMechanic(key);
        if (mechanic.isEmpty()) {
            return MechanicResult.failure("Mechanic not found: " + key, 0);
        }
        return executeMechanic(mechanic.get(), context);
    }

    public MechanicResult executeMechanic(Mechanic mechanic, MechanicContext context) {
        if (!mechanic.isEnabled()) {
            return MechanicResult.failure("Mechanic is disabled: " + mechanic.getId(), 0);
        }
        
        long startTime = System.nanoTime();
        try {
            MechanicResult result = mechanic.execute(context);
            long endTime = System.nanoTime();
            long executionTime = (endTime - startTime) / 1_000_000; // Convert to milliseconds
            
            plugin.getVoxelLogger().debug("Executed mechanic " + mechanic.getId() + " in " + executionTime + "ms");
            return result;
        } catch (Exception e) {
            long endTime = System.nanoTime();
            long executionTime = (endTime - startTime) / 1_000_000;
            
            plugin.getVoxelLogger().error("Error executing mechanic " + mechanic.getId() + ": " + e.getMessage(), e);
            return MechanicResult.failure("Execution error: " + e.getMessage(), executionTime);
        }
    }

    public Set<NamespacedKey> getLoadedMechanics() {
        return new HashSet<>(mechanics.keySet());
    }

    public boolean isHotReloadEnabled() {
        return plugin.getConfiguration().isHotReloadEnabled();
    }
}