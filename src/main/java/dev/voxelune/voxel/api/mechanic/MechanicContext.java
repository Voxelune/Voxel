package dev.voxelune.voxel.api.mechanic;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Context object passed to mechanic executions containing all relevant data.
 */
public class MechanicContext {
    
    private final Entity caster;
    private final Location origin;
    private final Map<String, Object> variables;
    private final Map<String, Object> metadata;

    private MechanicContext(Builder builder) {
        this.caster = builder.caster;
        this.origin = builder.origin;
        this.variables = new HashMap<>(builder.variables);
        this.metadata = new HashMap<>(builder.metadata);
    }

    public Optional<Entity> getCaster() { return Optional.ofNullable(caster); }
    public Optional<Player> getCasterAsPlayer() {
        return caster instanceof Player ? Optional.of((Player) caster) : Optional.empty();
    }
    public Optional<Location> getOrigin() { return Optional.ofNullable(origin); }
    
    public Map<String, Object> getVariables() { return new HashMap<>(variables); }
    public Optional<Object> getVariable(String key) { return Optional.ofNullable(variables.get(key)); }
    
    public Map<String, Object> getMetadata() { return new HashMap<>(metadata); }
    public Optional<Object> getMetadata(String key) { return Optional.ofNullable(metadata.get(key)); }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Entity caster;
        private Location origin;
        private final Map<String, Object> variables = new HashMap<>();
        private final Map<String, Object> metadata = new HashMap<>();

        public Builder caster(Entity caster) { this.caster = caster; return this; }
        public Builder origin(Location origin) { this.origin = origin; return this; }
        public Builder variable(String key, Object value) { variables.put(key, value); return this; }
        public Builder variables(Map<String, Object> variables) { this.variables.putAll(variables); return this; }
        public Builder metadata(String key, Object value) { metadata.put(key, value); return this; }
        public Builder metadata(Map<String, Object> metadata) { this.metadata.putAll(metadata); return this; }

        public MechanicContext build() {
            return new MechanicContext(this);
        }
    }
}