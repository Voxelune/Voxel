package dev.voxelune.voxel.core.capability;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.capability.Capability;
import dev.voxelune.voxel.api.capability.CapabilityProvider;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages capability registration and resolution.
 */
public class CapabilityManager {
    
    private final VoxelPlugin plugin;
    private final Map<Capability<?>, List<CapabilityProvider<?>>> providers;

    public CapabilityManager(VoxelPlugin plugin) {
        this.plugin = plugin;
        this.providers = new ConcurrentHashMap<>();
    }

    public void initialize() {
        plugin.getVoxelLogger().info("Initializing capability manager...");
        
        // Register a dummy capability for testing
        Capability<String> testCap = new Capability<>(
            new org.bukkit.NamespacedKey("voxel", "test"), 
            String.class, 
            "1.0.0"
        );
        
        CapabilityProvider<String> testProvider = new CapabilityProvider<String>() {
            @Override
            public Capability<String> getCapability() { return testCap; }
            @Override
            public Plugin getPlugin() { return plugin; }
            @Override
            public String getInstance() { return "Test Implementation"; }
        };
        
        registerProvider(plugin, testCap, testProvider);
        
        plugin.getVoxelLogger().info("Capability manager initialized");
    }

    public void shutdown() {
        providers.clear();
    }

    @SuppressWarnings("unchecked")
    public <T> void registerProvider(Plugin plugin, Capability<T> capability, CapabilityProvider<T> provider) {
        providers.computeIfAbsent(capability, k -> new ArrayList<>())
                .add((CapabilityProvider<?>) provider);
        
        this.plugin.getVoxelLogger().debug("Registered capability provider: " + capability);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<CapabilityProvider<T>> resolve(Capability<T> capability) {
        List<CapabilityProvider<?>> providerList = providers.get(capability);
        if (providerList == null || providerList.isEmpty()) {
            return Optional.empty();
        }
        
        // Return the first available provider
        return providerList.stream()
                .filter(CapabilityProvider::isAvailable)
                .map(p -> (CapabilityProvider<T>) p)
                .findFirst();
    }

    @SuppressWarnings("unchecked")
    public <T> Set<CapabilityProvider<T>> resolveAll(Capability<T> capability) {
        List<CapabilityProvider<?>> providerList = providers.get(capability);
        if (providerList == null) {
            return Set.of();
        }
        
        return providerList.stream()
                .filter(CapabilityProvider::isAvailable)
                .map(p -> (CapabilityProvider<T>) p)
                .collect(java.util.stream.Collectors.toSet());
    }

    public Set<Capability<?>> getRegisteredCapabilities() {
        return new HashSet<>(providers.keySet());
    }

    public <T> boolean hasCapability(Capability<T> capability) {
        return providers.containsKey(capability);
    }

    public void unregisterPlugin(Plugin plugin) {
        providers.values().forEach(list -> 
            list.removeIf(provider -> provider.getPlugin().equals(plugin)));
    }
}