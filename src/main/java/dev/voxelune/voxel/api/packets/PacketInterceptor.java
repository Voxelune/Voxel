package dev.voxelune.voxel.api.packets;

import org.bukkit.entity.Player;

/**
 * Interface for intercepting packets.
 */
public interface PacketInterceptor {
    
    /**
     * Called when a packet is intercepted.
     * 
     * @param player the player involved
     * @param packet the packet data
     * @return true to cancel the packet
     */
    boolean onPacket(Player player, Object packet);
}