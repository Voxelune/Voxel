package dev.voxelune.voxel.api.definitions;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Defines behavior for custom blocks.
 */
public interface BlockBehavior {
    
    BlockBehavior NONE = new BlockBehavior() {};
    
    default void onPlace(Player player, Block block, BlockPlaceEvent event) {}
    default void onBreak(Player player, Block block, BlockBreakEvent event) {}
    default void onInteract(Player player, Block block) {}
}