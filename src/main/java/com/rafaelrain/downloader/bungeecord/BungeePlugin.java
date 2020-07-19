package com.rafaelrain.downloader.bungeecord;

import com.rafaelrain.downloader.bungeecord.command.BungeeCommand;
import com.rafaelrain.downloader.core.Downloader;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public class BungeePlugin extends Plugin {

    @Getter
    private static BungeePlugin instance;

    @Getter
    private final Downloader downloader = new Downloader(new File("downloads"));

    @Override
    public void onEnable() {
        getLogger().info("Iniciando BungeePlugin Downloader...");
        instance = this;

        getProxy().getPluginManager().registerCommand(this, new BungeeCommand());
    }

    @Override
    public void onDisable() {
        downloader.disable();
    }
}
