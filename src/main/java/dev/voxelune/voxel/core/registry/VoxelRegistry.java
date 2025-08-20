package dev.voxelune.voxel.core.registry;

import dev.voxelune.voxel.api.registry.Registry;
import dev.voxelune.voxel.api.registry.RegistryType;
import org.bukkit.NamespacedKey;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default implementation of the Registry interface.
 */
public class VoxelRegistry<T> implements Registry<T> {
    
    private final RegistryType<T> type;
    private final Map<NamespacedKey, T> entries;

    public VoxelRegistry(RegistryType<T> type) {
        this.type = type;
        this.entries = new ConcurrentHashMap<>();
    }

    @Override
    public void register(NamespacedKey key, T object) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (object == null) throw new IllegalArgumentException("Object cannot be null");
        
        entries.put(key, object);
    }

    @Override
    public Optional<T> get(NamespacedKey key) {
        return Optional.ofNullable(entries.get(key));
    }

    @Override
    public boolean contains(NamespacedKey key) {
        return entries.containsKey(key);
    }

    @Override
    public Optional<T> remove(NamespacedKey key) {
        return Optional.ofNullable(entries.remove(key));
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return new HashSet<>(entries.keySet());
    }

    @Override
    public RegistryType<T> getType() {
        return type;
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }
}