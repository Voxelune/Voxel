package dev.voxelune.voxel.api.shops;

/**
 * API for economy and trading systems.
 */
public interface ShopAPI {
    
    /**
     * Register a shop.
     */
    void registerShop(Shop shop);
    
    /**
     * Get a shop by ID.
     */
    Shop getShop(String shopId);
}