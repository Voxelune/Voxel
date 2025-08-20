package dev.voxelune.voxel.core.mechanics.nodes;

import dev.voxelune.voxel.api.mechanics.*;

import java.util.Map;

public class HasStatNode implements MechanicNode {
    @Override
    public MechanicResult execute(MechanicContext context, Map<String, Object> args) {
        // Mock implementation
        return MechanicResult.success(1, 1);
    }
    
    @Override
    public String getType() {
        return "condition.has_stat";
    }
}