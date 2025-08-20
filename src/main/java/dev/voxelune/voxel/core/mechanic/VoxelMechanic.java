package dev.voxelune.voxel.core.mechanic;

import dev.voxelune.voxel.api.mechanic.Mechanic;
import dev.voxelune.voxel.api.mechanic.MechanicContext;
import dev.voxelune.voxel.api.mechanic.MechanicMetadata;
import dev.voxelune.voxel.api.mechanic.MechanicResult;
import org.bukkit.NamespacedKey;

import java.util.List;
import java.util.Map;

/**
 * Default implementation of a mechanic.
 */
public class VoxelMechanic implements Mechanic {
    
    private final NamespacedKey id;
    private final String version;
    private final boolean enabled;
    private final String description;
    private final List<Map<String, Object>> graphData;
    private final MechanicMetadata metadata;

    public VoxelMechanic(NamespacedKey id, String version, boolean enabled, String description,
                        List<Map<String, Object>> graphData, Map<String, Object> metadataMap) {
        this.id = id;
        this.version = version;
        this.enabled = enabled;
        this.description = description;
        this.graphData = List.copyOf(graphData);
        this.metadata = parseMetadata(metadataMap);
    }

    @SuppressWarnings("unchecked")
    private MechanicMetadata parseMetadata(Map<String, Object> metadataMap) {
        String category = (String) metadataMap.getOrDefault("category", "general");
        List<String> tags = (List<String>) metadataMap.getOrDefault("tags", List.of());
        int costMana = (Integer) metadataMap.getOrDefault("cost_mana", 0);
        double castTime = ((Number) metadataMap.getOrDefault("cast_time", 0.0)).doubleValue();
        
        return MechanicMetadata.builder()
            .category(category)
            .tags(tags)
            .costMana(costMana)
            .castTime(castTime)
            .additionalData(metadataMap)
            .build();
    }

    @Override
    public NamespacedKey getId() { return id; }

    @Override
    public String getVersion() { return version; }

    @Override
    public boolean isEnabled() { return enabled; }

    @Override
    public String getDescription() { return description; }

    @Override
    public MechanicResult execute(MechanicContext context) {
        long startTime = System.nanoTime();
        
        // Simple execution simulation - in a real implementation, this would
        // process the graph nodes and execute conditions, targets, and effects
        try {
            // Mock execution logic
            if (graphData.isEmpty()) {
                long executionTime = (System.nanoTime() - startTime) / 1_000_000;
                return MechanicResult.failure("Empty graph", executionTime);
            }
            
            // Simulate successful execution
            List<Object> mockTargets = List.of("mock_target_1", "mock_target_2");
            long executionTime = (System.nanoTime() - startTime) / 1_000_000;
            
            return MechanicResult.success(mockTargets, executionTime);
            
        } catch (Exception e) {
            long executionTime = (System.nanoTime() - startTime) / 1_000_000;
            return MechanicResult.failure("Execution error: " + e.getMessage(), executionTime);
        }
    }

    @Override
    public MechanicMetadata getMetadata() { return metadata; }
}