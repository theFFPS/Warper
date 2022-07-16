package org.foutry.warper;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.foutry.warper.api.Warp;

public class Executor implements CommandExecutor {
    public void invokeHelp(Player sender, String command) {
        sender.sendMessage(ChatColor.YELLOW + "/" + command + " help                 This message");
        sender.sendMessage(ChatColor.YELLOW + "/" + command + " warp <WARP_POINT>    Warp to <WARP_POINT>");
        if (sender.hasPermission("warper.setwarp")) sender.sendMessage(ChatColor.YELLOW + "/" + command + " setWarp <WARP_NAME>  Set warp with <WARP_NAME>");
        if (sender.hasPermission("warper.delwarp")) sender.sendMessage(ChatColor.YELLOW + "/" + command + " delWarp <WARP_NAME>  Remove warp with <WARP_NAME>");
        if (sender.hasPermission("warper.reload")) sender.sendMessage(ChatColor.YELLOW + "/" + command + " reload               Reload plugin config");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.notPlayer"));
            return true;
        }
        Player player = (Player) sender;
        try {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission("warper.reload")) { player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.noPerm")); return true; }
                Warper.getInstance().reloadConfig();
            } else if (args[0].equalsIgnoreCase("help")) invokeHelp(player, label);
            else if (args[0].equalsIgnoreCase("warp")) Warp.warp(player, args[1]);
            else if (args[0].equalsIgnoreCase("setWarp")) {
                if (!player.hasPermission("warper.setwarp")) { player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.noPerm")); return true; }
                Warp.setWarp(player, args[1]);
            } else if (args[0].equalsIgnoreCase("delWarp")) {
                if (!player.hasPermission("warper.delwarp")) { player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.noPerm")); return true; }
                Warp.delWarp(player, args[1]);
            } else {
                sender.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.subCommandNotFound").replace("${SUBCMD}", args[0]).replace("${CMD}", label));
                return true;
            }
        } catch (Exception e) { invokeHelp(player, label); e.printStackTrace(); }
        return false;
    }
}
