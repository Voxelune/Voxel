package dev.voxelune.voxel.command;

import dev.voxelune.voxel.VoxelPlugin;
import dev.voxelune.voxel.api.VoxelAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Main command handler for the Voxel Framework.
 * 
 * Provides developer-focused commands for framework management and debugging.
 */
public class VoxelCommand implements CommandExecutor, TabCompleter {
    
    private final VoxelPlugin plugin;

    public VoxelCommand(VoxelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendFrameworkInfo(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload" -> handleReload(sender);
            case "devinfo" -> handleDevInfo(sender);
            default -> {
                sender.sendMessage("§cUnknown subcommand: " + args[0]);
                sender.sendMessage("§7Usage: /voxel <reload|devinfo>");
                return false;
            }
        }
        
        return true;
    }

    private void sendFrameworkInfo(CommandSender sender) {
        VoxelAPI api = plugin.getApi();
        
        sender.sendMessage("§6╔══════════════════════════════════════╗");
        sender.sendMessage("§6║        §fVoxel Framework v" + api.getVersion() + "        §6║");
        sender.sendMessage("§6║   §7Ultimate Plugin Developer Framework  §6║");
        sender.sendMessage("§6╚══════════════════════════════════════╝");
        sender.sendMessage("");
        sender.sendMessage("§7Status: §a" + (api.isReady() ? "Ready" : "Loading..."));
        sender.sendMessage("§7Developer Mode: " + (api.isDeveloperMode() ? "§aEnabled" : "§cDisabled"));
        sender.sendMessage("§7Framework: §fForge/Fabric-like experience for Paper");
        sender.sendMessage("");
        sender.sendMessage("§6Available Commands:");
        sender.sendMessage("§8  /voxel reload §7- Reload framework and definitions");
        sender.sendMessage("§8  /voxel devinfo §7- Show detailed developer information");
        sender.sendMessage("");
        sender.sendMessage("§7Documentation: §bhttps://github.com/voxelune/voxel/wiki");
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("voxel.dev.reload")) {
            sender.sendMessage("§cYou don't have permission to reload the framework.");
            return;
        }
        
        sender.sendMessage("§7Reloading Voxel Framework...");
        long startTime = System.currentTimeMillis();
        
        try {
            plugin.reload();
            long duration = System.currentTimeMillis() - startTime;
            sender.sendMessage("§aFramework reloaded successfully in " + duration + "ms!");
        } catch (Exception e) {
            sender.sendMessage("§cReload failed: " + e.getMessage());
            plugin.getVoxelLogger().error("Reload failed", e);
        }
    }

    private void handleDevInfo(CommandSender sender) {
        if (!sender.hasPermission("voxel.dev.info")) {
            sender.sendMessage("§cYou don't have permission to view developer information.");
            return;
        }
        
        VoxelAPI api = plugin.getApi();
        var framework = plugin.getFramework();
        var config = plugin.getConfiguration();
        
        sender.sendMessage("§6═══ Voxel Framework Developer Information ═══");
        sender.sendMessage("");
        sender.sendMessage("§7Framework Status:");
        sender.sendMessage("§8  Version: §f" + api.getVersion());
        sender.sendMessage("§8  Ready: §f" + api.isReady());
        sender.sendMessage("§8  Developer Mode: §f" + api.isDeveloperMode());
        sender.sendMessage("§8  Hot Reload: §f" + config.isHotReloadEnabled());
        sender.sendMessage("");
        sender.sendMessage("§7Active Modules:");
        sender.sendMessage("§8  Registry: §f" + config.isModuleEnabled("registry"));
        sender.sendMessage("§8  Mechanics: §f" + config.isModuleEnabled("mechanics"));
        sender.sendMessage("§8  Packets: §f" + config.isModuleEnabled("packets"));
        sender.sendMessage("§8  Models: §f" + config.isModuleEnabled("models"));
        sender.sendMessage("§8  Stats: §f" + config.isModuleEnabled("stats"));
        sender.sendMessage("§8  Profiles: §f" + config.isModuleEnabled("profiles"));
        sender.sendMessage("§8  Jobs: §f" + config.isModuleEnabled("jobs"));
        sender.sendMessage("§8  Shops: §f" + config.isModuleEnabled("shops"));
        sender.sendMessage("§8  Dungeons: §f" + config.isModuleEnabled("dungeons"));
        sender.sendMessage("§8  Worldgen: §f" + config.isModuleEnabled("worldgen"));
        sender.sendMessage("§8  Scripting: §f" + config.isModuleEnabled("scripting"));
        sender.sendMessage("§8  Editor: §f" + config.isModuleEnabled("editor_integration"));
        sender.sendMessage("");
        sender.sendMessage("§7Performance:");
        sender.sendMessage("§8  Async I/O: §f" + config.isAsyncIOEnabled());
        sender.sendMessage("§8  Registry Cache: §f" + config.getRegistryCacheSize());
        sender.sendMessage("§8  Mechanic Timeout: §f" + config.getMechanicExecutionTimeout() + "ms");
        sender.sendMessage("§8  Script Timeout: §f" + config.getScriptExecutionTimeout() + "ms");
        sender.sendMessage("");
        sender.sendMessage("§7Memory Usage:");
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;
        sender.sendMessage("§8  Used: §f" + usedMemory + "MB");
        sender.sendMessage("§8  Free: §f" + freeMemory + "MB");
        sender.sendMessage("§8  Total: §f" + totalMemory + "MB");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            String[] subCommands = {"reload", "devinfo"};
            for (String sub : subCommands) {
                if (sub.startsWith(args[0].toLowerCase())) {
                    completions.add(sub);
                }
            }
        }
        
        Collections.sort(completions);
        return completions;
    }
}