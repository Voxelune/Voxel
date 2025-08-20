package dev.voxelune.voxel.api.mechanics;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Map;

/**
 * Context for mechanic execution.
 */
public interface MechanicContext {
    
    Entity getCaster();
    Location getOrigin();
    Map<String, Object> getVariables();
    
    static Builder builder() {
        return new Builder();
    }
    
    class Builder {
        private Entity caster;
        private Location origin;
        private Map<String, Object> variables = Map.of();
        
        public Builder caster(Entity caster) { this.caster = caster; return this; }
        public Builder origin(Location origin) { this.origin = origin; return this; }
        public Builder variables(Map<String, Object> variables) { this.variables = variables; return this; }
        
        public MechanicContext build() {
            return new MechanicContextImpl(caster, origin, variables);
        }
    }
}