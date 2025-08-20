package dev.voxelune.voxel.api.definitions;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Defines behavior for custom items.
 * 
 * This interface allows items to have custom behavior when used,
 * similar to how Forge items work.
 */
public interface ItemBehavior {
    
    /**
     * No-op behavior for items that don't need custom behavior.
     */
    ItemBehavior NONE = new ItemBehavior() {};
    
    /**
     * Called when a player right-clicks with this item.
     * 
     * @param player the player using the item
     * @param item the item stack being used
     * @param event the interaction event
     * @return true to cancel the event
     */
    default boolean onUse(Player player, ItemStack item, PlayerInteractEvent event) {
        return false;
    }
    
    /**
     * Called when a player left-clicks with this item.
     * 
     * @param player the player using the item
     * @param item the item stack being used
     * @param event the interaction event
     * @return true to cancel the event
     */
    default boolean onAttack(Player player, ItemStack item, PlayerInteractEvent event) {
        return false;
    }
    
    /**
     * Called when this item is crafted.
     * 
     * @param player the player crafting the item
     * @param item the crafted item
     */
    default void onCraft(Player player, ItemStack item) {}
    
    /**
     * Called when this item is dropped.
     * 
     * @param player the player dropping the item
     * @param item the dropped item
     */
    default void onDrop(Player player, ItemStack item) {}
    
    /**
     * Called when this item is picked up.
     * 
     * @param player the player picking up the item
     * @param item the picked up item
     */
    default void onPickup(Player player, ItemStack item) {}
}