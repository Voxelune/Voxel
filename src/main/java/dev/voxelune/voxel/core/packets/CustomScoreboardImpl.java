package dev.voxelune.voxel.core.packets;

import dev.voxelune.voxel.api.packets.CustomScoreboard;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of CustomScoreboard.
 */
public class CustomScoreboardImpl implements CustomScoreboard {
    
    private String title;
    private final Map<Integer, String> lines = new ConcurrentHashMap<>();

    public CustomScoreboardImpl(String title) {
        this.title = title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setLine(int line, String text) {
        lines.put(line, text);
    }

    @Override
    public void removeLine(int line) {
        lines.remove(line);
    }

    @Override
    public void show(Player player) {
        // Mock implementation
    }

    @Override
    public void hide(Player player) {
        // Mock implementation
    }
}