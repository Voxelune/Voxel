package dev.voxelune.voxel.api.registry;

import org.bukkit.NamespacedKey;

/**
 * Represents a type of registry that can hold specific types of objects.
 */
public class RegistryType<T> {
    
    private final NamespacedKey key;
    private final Class<T> type;
    private final String description;

    public RegistryType(NamespacedKey key, Class<T> type, String description) {
        this.key = key;
        this.type = type;
        this.description = description;
    }

    public NamespacedKey getKey() { return key; }
    public Class<T> getType() { return type; }
    public String getDescription() { return description; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RegistryType<?> other)) return false;
        return key.equals(other.key) && type.equals(other.type);
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 31 + type.hashCode();
    }

    @Override
    public String toString() {
        return "RegistryType{" + key + " -> " + type.getSimpleName() + "}";
    }

    // Common registry types
    public static final RegistryType<Object> SKILLS = new RegistryType<>(
        new NamespacedKey("voxel", "skills"), Object.class, "Player skills and abilities");
    public static final RegistryType<Object> ITEMS = new RegistryType<>(
        new NamespacedKey("voxel", "items"), Object.class, "Custom items and weapons");
    public static final RegistryType<Object> MOBS = new RegistryType<>(
        new NamespacedKey("voxel", "mobs"), Object.class, "Custom mob types");
    public static final RegistryType<Object> SHOPS = new RegistryType<>(
        new NamespacedKey("voxel", "shops"), Object.class, "NPC shops and vendors");
    public static final RegistryType<Object> DUNGEONS = new RegistryType<>(
        new NamespacedKey("voxel", "dungeons"), Object.class, "Dungeon instances");
    public static final RegistryType<Object> WORLDGEN = new RegistryType<>(
        new NamespacedKey("voxel", "worldgen"), Object.class, "World generation features");
}