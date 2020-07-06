package com.rafaelrain.downloader.bungeecord.command;

import com.rafaelrain.downloader.core.Downloader;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.apache.commons.lang.math.NumberUtils;

import java.io.File;
import java.net.MalformedURLException;

public class BungeeCommand extends Command {

    private final Downloader downloader = new Downloader(new File("downloads"));

    public BungeeCommand() {
        super("download", "download.use", "downloadFiles");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(
                    "§eUtilize: §f/Download <url> [<quantia>]"
            );
            return;
        }

        int times = 1;
        if (args.length == 2) {
            if (NumberUtils.isNumber(args[1])) {
                times = Integer.parseInt(args[1]);
            } else return;
        }
        try {
            downloader.addToQueue(args[0], times);
        } catch (MalformedURLException e) {
            sender.sendMessage("Erro ao tentar ler como url, tente novamente.");
        }
    }
}
