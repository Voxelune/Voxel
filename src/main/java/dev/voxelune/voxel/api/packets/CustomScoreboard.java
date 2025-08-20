package dev.voxelune.voxel.api.packets;

import org.bukkit.entity.Player;

/**
 * Custom scoreboard implementation.
 */
public interface CustomScoreboard {
    
    void setTitle(String title);
    void setLine(int line, String text);
    void removeLine(int line);
    void show(Player player);
    void hide(Player player);
}