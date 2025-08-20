package dev.voxelune.voxel.command;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.CapabilityAPI;
import dev.voxelune.voxel.api.MechanicAPI;
import dev.voxelune.voxel.api.RegistryAPI;
import dev.voxelune.voxel.api.capability.Capability;
import dev.voxelune.voxel.api.mechanic.MechanicContext;
import dev.voxelune.voxel.api.mechanic.MechanicResult;
import dev.voxelune.voxel.api.registry.RegistryType;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Main command handler for Voxel.
 */
public class VoxelCommand implements CommandExecutor, TabCompleter {
    
    private final VoxelPlugin plugin;

    public VoxelCommand(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> handleReload(sender);
            case "cap" -> handleCapability(sender, Arrays.copyOfRange(args, 1, args.length));
            case "reg" -> handleRegistry(sender, Arrays.copyOfRange(args, 1, args.length));
            case "test" -> handleTest(sender, Arrays.copyOfRange(args, 1, args.length));
            default -> {
                sender.sendMessage("§cUnknown subcommand: " + args[0]);
                return false;
            }
        }
        
        return true;
    }

    private void sendInfo(CommandSender sender) {
        sender.sendMessage("§6=== Voxel Core Library ===");
        sender.sendMessage("§7Version: §f" + plugin.getDescription().getVersion());
        sender.sendMessage("§7Author: §f" + plugin.getDescription().getAuthors().get(0));
        sender.sendMessage("§7Status: §a" + (plugin.getApi().isReady() ? "Ready" : "Loading..."));
        sender.sendMessage("");
        sender.sendMessage("§7Commands:");
        sender.sendMessage("§8  /voxel reload §7- Reload configuration and mechanics");
        sender.sendMessage("§8  /voxel cap list §7- List registered capabilities");
        sender.sendMessage("§8  /voxel reg list <registry> §7- List registry entries");
        sender.sendMessage("§8  /voxel test run <mechanicId> §7- Test a mechanic");
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("voxel.admin.reload")) {
            sender.sendMessage("§cYou don't have permission to reload Voxel.");
            return;
        }
        
        sender.sendMessage("§7Reloading Voxel...");
        try {
            plugin.reload();
            sender.sendMessage("§aVoxel reloaded successfully!");
        } catch (Exception e) {
            sender.sendMessage("§cReload failed: " + e.getMessage());
        }
    }

    private void handleCapability(CommandSender sender, String[] args) {
        if (!sender.hasPermission("voxel.admin.inspect")) {
            sender.sendMessage("§cYou don't have permission to inspect capabilities.");
            return;
        }
        
        if (args.length == 0 || !args[0].equals("list")) {
            sender.sendMessage("§cUsage: /voxel cap list");
            return;
        }

        CapabilityAPI capApi = plugin.getApi().getCapabilityAPI();
        var capabilities = capApi.getRegisteredCapabilities();
        
        sender.sendMessage("§6=== Registered Capabilities ===");
        if (capabilities.isEmpty()) {
            sender.sendMessage("§7No capabilities registered.");
        } else {
            for (Capability<?> cap : capabilities) {
                sender.sendMessage("§8  - §f" + cap.getKey().toString());
            }
        }
    }

    private void handleRegistry(CommandSender sender, String[] args) {
        if (!sender.hasPermission("voxel.admin.inspect")) {
            sender.sendMessage("§cYou don't have permission to inspect registries.");
            return;
        }
        
        if (args.length < 2 || !args[0].equals("list")) {
            sender.sendMessage("§cUsage: /voxel reg list <registry>");
            return;
        }

        RegistryAPI regApi = plugin.getApi().getRegistryAPI();
        var registryTypes = regApi.getRegistryTypes();
        
        sender.sendMessage("§6=== Registry Types ===");
        if (registryTypes.isEmpty()) {
            sender.sendMessage("§7No registries available.");
        } else {
            for (RegistryType<?> type : registryTypes) {
                sender.sendMessage("§8  - §f" + type.getKey().toString());
            }
        }
    }

    private void handleTest(CommandSender sender, String[] args) {
        if (!sender.hasPermission("voxel.dev.test")) {
            sender.sendMessage("§cYou don't have permission to test mechanics.");
            return;
        }
        
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can test mechanics.");
            return;
        }
        
        if (args.length < 2 || !args[0].equals("run")) {
            sender.sendMessage("§cUsage: /voxel test run <mechanicId>");
            return;
        }

        String mechanicId = args[1];
        NamespacedKey key;
        
        try {
            if (mechanicId.contains(":")) {
                key = NamespacedKey.fromString(mechanicId);
            } else {
                key = new NamespacedKey("voxel", mechanicId);
            }
        } catch (Exception e) {
            sender.sendMessage("§cInvalid mechanic ID: " + mechanicId);
            return;
        }

        MechanicAPI mechApi = plugin.getApi().getMechanicAPI();
        MechanicContext context = MechanicContext.builder()
            .caster(player)
            .origin(player.getLocation())
            .build();

        sender.sendMessage("§7Testing mechanic: §f" + key);
        MechanicResult result = mechApi.executeMechanic(key, context);
        
        if (result.isSuccess()) {
            sender.sendMessage("§aMechanic executed successfully!");
            sender.sendMessage("§7Targets affected: §f" + result.getTargetsAffected());
        } else {
            sender.sendMessage("§cMechanic failed: " + result.getErrorMessage());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            String[] subCommands = {"reload", "cap", "reg", "test"};
            for (String sub : subCommands) {
                if (sub.startsWith(args[0].toLowerCase())) {
                    completions.add(sub);
                }
            }
        } else if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "cap", "reg" -> {
                    if ("list".startsWith(args[1].toLowerCase())) {
                        completions.add("list");
                    }
                }
                case "test" -> {
                    if ("run".startsWith(args[1].toLowerCase())) {
                        completions.add("run");
                    }
                }
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("test") && args[1].equalsIgnoreCase("run")) {
            // Tab complete mechanic IDs
            var mechanics = plugin.getApi().getMechanicAPI().getLoadedMechanics();
            for (NamespacedKey key : mechanics) {
                String keyStr = key.toString();
                if (keyStr.startsWith(args[2].toLowerCase())) {
                    completions.add(keyStr);
                }
            }
        }
        
        Collections.sort(completions);
        return completions;
    }
}