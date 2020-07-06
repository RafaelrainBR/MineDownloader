package com.rafaelrain.downloader.spigot;

import com.rafaelrain.downloader.spigot.command.DownloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Iniciando plugin Downloader...");
        getCommand("download").setExecutor(new DownloadCommand());
    }
}
