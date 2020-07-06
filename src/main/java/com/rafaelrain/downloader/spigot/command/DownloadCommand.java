package com.rafaelrain.downloader.spigot.command;

import com.rafaelrain.downloader.core.Downloader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.net.MalformedURLException;

public class DownloadCommand implements CommandExecutor {

    private final Downloader downloader = new Downloader(new File("downloads"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) return false;

        int times = 1;

        if(args.length == 2){
            try {
                times = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored){
                return false;
            }
        }

        try {
            downloader.addToQueue(args[0], times);
        } catch (MalformedURLException e) {
            sender.sendMessage("Erro ao tentar ler como url, tente novamente.");
        }

        return true;
    }
}
