package org.foutry.warper.api;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.foutry.warper.Warper;

public class Warp {
    public static void setWarp(Player player, String name) {
        if (Warper.getData().getConfig().getString("warps." + name) != null) {
            if (!player.getDisplayName().equals(Warper.getData().getConfig().getString("warps." + name + ".setter"))) {
                if (!player.hasPermission("warper.overridewarpofother")) {
                    player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.noPerm"));
                    return;
                }
            }
        }
        Warper.getData().getConfig().set("warps." + name + ".location", player.getLocation());
        Warper.getData().getConfig().set("warps." + name + ".setter", player.getDisplayName());
        Warper.getData().save();
        player.sendMessage(ChatColor.GREEN + Warper.getInstance().getConfig().getString("msg.warpSet").replace("${WARP}", name));
    }
    public static void delWarp(Player player, String name) {
        if (Warper.getData().getConfig().getString("warps." + name) == null || Warper.getData().getConfig().getString("warps." + name) == "") {
            player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.warpNotExists").replace("${WARP}", name));
            return;
        }
        if (!player.getDisplayName().equals(Warper.getData().getConfig().getString("warps." + name + ".setter"))) {
            if (!player.hasPermission("warper.delwarpofother")) {
                player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.noPerm"));
                return;
            }
        }
        Warper.getData().getConfig().set("warps." + name, null);
        Warper.getData().save();
        player.sendMessage(ChatColor.GREEN + Warper.getInstance().getConfig().getString("msg.warpRemoved").replace("${WARP}", name));
    }
    public static void warp(Player player, String name) {
        if (Warper.getData().getConfig().getString("warps." + name) == null || Warper.getData().getConfig().getString("warps." + name) == "") {
            player.sendMessage(ChatColor.RED + Warper.getInstance().getConfig().getString("msg.warpNotExists").replace("${WARP}", name));
            return;
        }
        player.teleport(Warper.getData().getConfig().getLocation("warps." + name + ".location"));
        player.sendMessage(ChatColor.GRAY + Warper.getInstance().getConfig().getString("msg.warped").replace("${WARP}", name));
    }
}
