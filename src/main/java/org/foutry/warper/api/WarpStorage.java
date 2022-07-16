package org.foutry.warper.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.foutry.warper.Warper;

import java.io.File;
import java.io.IOException;

public class WarpStorage {
    private File file;
    private FileConfiguration config;
    public WarpStorage(String name) {
        file = new File(Warper.getInstance().getDataFolder(), name);
        try { if (!file.exists() && !file.createNewFile()) throw new IOException(); } catch (IOException e) { throw new RuntimeException("Failed to create warps.yml", e); }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public FileConfiguration getConfig() { return config; }
    public void save() { try { config.save(file); } catch (IOException e) { throw new RuntimeException("Failed to save warps.yml", e); } }
}
