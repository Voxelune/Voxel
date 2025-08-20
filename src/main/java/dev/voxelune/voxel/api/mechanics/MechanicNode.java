package dev.voxelune.voxel.api.mechanics;

import java.util.Map;

/**
 * A node in a mechanic graph.
 */
public interface MechanicNode {
    
    /**
     * Execute this node with the given context and arguments.
     */
    MechanicResult execute(MechanicContext context, Map<String, Object> args);
    
    /**
     * Get the type identifier for this node.
     */
    String getType();
}