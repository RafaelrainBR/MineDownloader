package com.rafaelrain.downloader.spigot;

import com.rafaelrain.downloader.core.Downloader;
import com.rafaelrain.downloader.spigot.command.SpigotCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SpigotPlugin extends JavaPlugin {

    @Getter
    private final Downloader downloader = new Downloader(new File("downloads"));

    @Override
    public void onEnable() {
        getLogger().info("Iniciando SpigotPlugin Downloader...");
        getCommand("download").setExecutor(new SpigotCommand());
    }

    @Override
    public void onDisable() {
        downloader.disable();
    }
}
