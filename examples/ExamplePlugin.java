package dev.voxelune.examples;

import dev.voxelune.voxel.api.VoxelAPI;
import dev.voxelune.voxel.api.registry.VoxelRegistry;
import dev.voxelune.voxel.api.registry.RegistryObject;
import dev.voxelune.voxel.api.definitions.ItemDef;
import dev.voxelune.voxel.api.definitions.ItemBehavior;
import dev.voxelune.voxel.api.mechanics.MechanicContext;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Example plugin demonstrating Voxel Framework usage.
 * 
 * This shows how to:
 * - Access the Voxel API
 * - Register custom items with behavior
 * - Execute mechanics
 * - Use the packet API
 */
public class ExamplePlugin extends JavaPlugin {
    
    // Get registries
    private static final VoxelRegistry<ItemDef> ITEMS = getVoxelAPI().registry(ItemDef.class);
    
    // Register items
    public static final RegistryObject<ItemDef> MAGIC_WAND = ITEMS.register("magic_wand", () ->
        ItemDef.builder(new NamespacedKey("example", "magic_wand"))
            .displayName("§6Magic Wand")
            .lore(List.of("§7A powerful magical focus", "§7Right-click to cast firebolt"))
            .baseMaterial(Material.STICK)
            .customModelData(1001)
            .behavior(new MagicWandBehavior())
            .build()
    );
    
    @Override
    public void onEnable() {
        getLogger().info("Example plugin enabled with Voxel Framework!");
        
        // Example: Give magic wand to all online players
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                ItemStack wand = MAGIC_WAND.get().createItemStack();
                player.getInventory().addItem(wand);
                player.sendMessage("§aYou received a Magic Wand! Right-click to cast firebolt.");
            }
        }, 20L); // Wait 1 second for framework to initialize
    }
    
    private static VoxelAPI getVoxelAPI() {
        return Bukkit.getServicesManager().getRegistration(VoxelAPI.class).getProvider();
    }
    
    /**
     * Custom behavior for the magic wand.
     */
    private static class MagicWandBehavior implements ItemBehavior {
        
        @Override
        public boolean onUse(Player player, ItemStack item, PlayerInteractEvent event) {
            // Execute the firebolt mechanic
            VoxelAPI voxel = getVoxelAPI();
            
            MechanicContext context = MechanicContext.builder()
                .caster(player)
                .origin(player.getLocation())
                .build();
                
            NamespacedKey firebolKey = new NamespacedKey("voxel", "firebolt");
            var result = voxel.mechanics().execute(fireboltKey, context);
            
            if (result.isSuccess()) {
                player.sendMessage("§6Firebolt cast successfully!");
            } else {
                player.sendMessage("§cFailed to cast firebolt: " + result.getErrorMessage());
            }
            
            return true; // Cancel the event
        }
    }
}