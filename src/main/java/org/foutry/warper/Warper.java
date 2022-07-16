package org.foutry.warper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.foutry.warper.api.WarpStorage;

import java.util.logging.Logger;

public final class Warper extends JavaPlugin {
    Logger log = Bukkit.getLogger();
    private static Warper instance;
    private WarpStorage data;
    @Override
    public void onEnable() {
        instance = this;
        log.info("[WARPER] Enabled!");
        getCommand("warper").setExecutor(new Executor());
        getCommand("warp").setExecutor(new Executor());
        getCommand("w").setExecutor(new Executor());
        saveDefaultConfig();
        data = new WarpStorage("warps.yml");
    }
    @Override
    public void onDisable() { log.info("[WARPER] Bye!"); }
    public static Warper getInstance() { return instance; }
    public static WarpStorage getData() { return instance.data; }
}
