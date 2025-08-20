package dev.voxelune.voxel.core.mechanic.parser;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.mechanic.Mechanic;
import dev.voxelune.voxel.core.mechanic.VoxelMechanic;
import org.bukkit.NamespacedKey;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Parses mechanic definition files into executable mechanics.
 */
public class MechanicParser {
    
    private final VoxelPlugin plugin;
    private final Yaml yaml;

    public MechanicParser(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.yaml = new Yaml();
    }

    public Optional<Mechanic> parseFromFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            Map<String, Object> data = yaml.load(fis);
            return parseFromData(data);
        } catch (IOException e) {
            plugin.getVoxelLogger().error("Error reading mechanic file " + file.getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public Optional<Mechanic> parseFromData(Map<String, Object> data) {
        try {
            String idStr = (String) data.get("id");
            if (idStr == null) {
                plugin.getVoxelLogger().warning("Mechanic missing required 'id' field");
                return Optional.empty();
            }

            NamespacedKey id = NamespacedKey.fromString(idStr);
            if (id == null) {
                plugin.getVoxelLogger().warning("Invalid mechanic id: " + idStr);
                return Optional.empty();
            }

            String version = (String) data.getOrDefault("version", "1.0.0");
            boolean enabled = (Boolean) data.getOrDefault("enabled", true);
            String description = (String) data.getOrDefault("description", "");

            // Parse the graph
            List<Map<String, Object>> graphData = (List<Map<String, Object>>) data.get("graph");
            if (graphData == null || graphData.isEmpty()) {
                plugin.getVoxelLogger().warning("Mechanic " + id + " has no graph defined");
                return Optional.empty();
            }

            // Parse metadata if present
            Map<String, Object> metadataMap = (Map<String, Object>) data.getOrDefault("metadata", Map.of());

            VoxelMechanic mechanic = new VoxelMechanic(id, version, enabled, description, graphData, metadataMap);
            return Optional.of(mechanic);

        } catch (Exception e) {
            plugin.getVoxelLogger().error("Error parsing mechanic data: " + e.getMessage(), e);
            return Optional.empty();
        }
    }
}