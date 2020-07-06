package com.rafaelrain.downloader.spigot.command;

import com.rafaelrain.downloader.core.Downloader;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.net.MalformedURLException;

public class SpigotCommand implements CommandExecutor {

    private final Downloader downloader = new Downloader(new File("downloads"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        int times = 1;


        if(args.length == 2){
            if (NumberUtils.isNumber(args[1])) {
                times = Integer.parseInt(args[1]);
            } else return false;
        }

        try {
            downloader.addToQueue(args[0], times);
        } catch (MalformedURLException e) {
            sender.sendMessage("Erro ao tentar ler como url, tente novamente.");
        }

        return true;
    }
}
