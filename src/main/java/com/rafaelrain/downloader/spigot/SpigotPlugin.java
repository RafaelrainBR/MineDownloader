package com.rafaelrain.downloader.spigot;

import com.rafaelrain.downloader.spigot.command.SpigotCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Iniciando SpigotPlugin Downloader...");
        getCommand("download").setExecutor(new SpigotCommand());
    }
}
