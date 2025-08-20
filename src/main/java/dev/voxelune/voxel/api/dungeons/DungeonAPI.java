package dev.voxelune.voxel.api.dungeons;

/**
 * API for procedural dungeons.
 */
public interface DungeonAPI {
    
    /**
     * Generate a dungeon.
     */
    Dungeon generateDungeon(String dungeonType);
}