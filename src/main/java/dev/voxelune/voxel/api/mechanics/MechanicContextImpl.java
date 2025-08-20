package dev.voxelune.voxel.api.mechanics;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Map;

record MechanicContextImpl(Entity caster, Location origin, Map<String, Object> variables) implements MechanicContext {
    @Override
    public Entity getCaster() { return caster; }
    @Override
    public Location getOrigin() { return origin; }
    @Override
    public Map<String, Object> getVariables() { return variables; }
}