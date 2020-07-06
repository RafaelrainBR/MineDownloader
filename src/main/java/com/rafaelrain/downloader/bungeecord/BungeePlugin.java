package com.rafaelrain.downloader.bungeecord;

import com.rafaelrain.downloader.bungeecord.command.BungeeCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlugin extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("Iniciando BungeePlugin Downloader...");

        getProxy().getPluginManager().registerCommand(this, new BungeeCommand());
    }
}
