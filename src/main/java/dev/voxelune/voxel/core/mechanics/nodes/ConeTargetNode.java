package dev.voxelune.voxel.core.mechanics.nodes;

import dev.voxelune.voxel.api.mechanics.*;

import java.util.Map;

public class ConeTargetNode implements MechanicNode {
    @Override
    public MechanicResult execute(MechanicContext context, Map<String, Object> args) {
        return MechanicResult.success(1, 2);
    }
    
    @Override
    public String getType() {
        return "target.cone";
    }
}