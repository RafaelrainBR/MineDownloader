package com.rafaelrain.downloader.sponge;

import com.google.inject.Inject;
import com.rafaelrain.downloader.core.Downloader;
import com.rafaelrain.downloader.sponge.command.SpongeCommand;
import lombok.Getter;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.File;


@Plugin(
        id = "downloader",
        name = "Java Downloader",
        version = "1.0-SNAPSHOT"
)
public class SpongePlugin {

    @Getter
    private static SpongePlugin instance;

    @Inject
    private Logger logger;

    @Getter
    private final Downloader downloader = new Downloader(new File("downloads"));

    @Listener
    public void onGameStart(GameInitializationEvent e) {
        logger.info("Initializing");
        instance = this;

        final SpongeCommand command = new SpongeCommand();
        Sponge.getCommandManager().register(
                this,
                command.getCommandSpec(),
                "download", "downloadfiles"
        );
    }

    @Listener
    public void onDisable(GameStoppingServerEvent e) {
        downloader.disable();
    }
}
