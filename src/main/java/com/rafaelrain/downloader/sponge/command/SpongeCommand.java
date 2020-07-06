package com.rafaelrain.downloader.sponge.command;


import com.rafaelrain.downloader.core.Downloader;
import lombok.Getter;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import java.io.File;
import java.net.URL;

public class SpongeCommand implements CommandExecutor {

    @Getter
    private final CommandSpec commandSpec = CommandSpec
            .builder()
            .executor(this)
            .arguments(
                    GenericArguments.onlyOne(GenericArguments.url(Text.of("url"))),
                    GenericArguments.optional(GenericArguments.integer(Text.of("times")), 1)
            )
            .permission("download.use")
            .build();

    private final Downloader downloader = new Downloader(new File("downloads"));

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        URL url = args.<URL>getOne("url").get();
        int times = args.<Integer>getOne("times").get();

        downloader.addToQueue(url, times);

        return CommandResult.success();
    }
}
