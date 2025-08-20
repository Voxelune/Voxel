package dev.voxelune.voxel.api.capability;

import org.bukkit.NamespacedKey;

/**
 * Represents a capability that can be provided and resolved.
 */
public class Capability<T> {
    
    private final NamespacedKey key;
    private final Class<T> type;
    private final String version;

    public Capability(NamespacedKey key, Class<T> type, String version) {
        this.key = key;
        this.type = type;
        this.version = version;
    }

    public NamespacedKey getKey() { return key; }
    public Class<T> getType() { return type; }
    public String getVersion() { return version; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Capability<?> other)) return false;
        return key.equals(other.key) && type.equals(other.type);
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 31 + type.hashCode();
    }

    @Override
    public String toString() {
        return "Capability{" + key + " -> " + type.getSimpleName() + " v" + version + "}";
    }
}