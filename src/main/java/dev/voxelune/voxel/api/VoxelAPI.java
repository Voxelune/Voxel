package dev.voxelune.voxel.api;

/**
 * Root entry point for the Voxel API.
 * Access this via Bukkit's Services Manager.
 */
public interface VoxelAPI {
    
    /**
     * Get the Registry API for managing typed registries.
     */
    RegistryAPI getRegistryAPI();
    
    /**
     * Get the Mechanic API for compiling and executing mechanic graphs.
     */
    MechanicAPI getMechanicAPI();
    
    /**
     * Get the Capability API for plugin feature registration and resolution.
     */
    CapabilityAPI getCapabilityAPI();
    
    /**
     * Get the Model API for model, sound, and particle routing.
     */
    ModelAPI getModelAPI();
    
    /**
     * Get the Stat API for defining and managing stats and elements.
     */
    StatAPI getStatAPI();
    
    /**
     * Get the version of the Voxel API.
     */
    String getVersion();
    
    /**
     * Check if the API is ready for use.
     */
    boolean isReady();
}