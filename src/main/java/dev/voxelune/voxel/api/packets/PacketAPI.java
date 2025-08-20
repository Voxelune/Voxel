package dev.voxelune.voxel.api.packets;

import org.bukkit.entity.Player;

/**
 * Built-in packet manipulation API.
 * 
 * Provides ProtocolLib-like functionality without external dependencies.
 */
public interface PacketAPI {
    
    /**
     * Send a fake entity to a player.
     */
    void sendFakeEntity(Player player, FakeEntity entity);
    
    /**
     * Send an action bar message.
     */
    void sendActionBar(Player player, String message);
    
    /**
     * Create a custom scoreboard.
     */
    CustomScoreboard createScoreboard(String title);
    
    /**
     * Intercept incoming packets.
     */
    void interceptIncoming(PacketInterceptor interceptor);
    
    /**
     * Intercept outgoing packets.
     */
    void interceptOutgoing(PacketInterceptor interceptor);
}