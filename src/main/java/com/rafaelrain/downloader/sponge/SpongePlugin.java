package com.rafaelrain.downloader.sponge;

import com.google.inject.Inject;
import com.rafaelrain.downloader.sponge.command.SpongeCommand;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;


@Plugin(
        id = "Downloader",
        name = "Java Downloader",
        version = "1.0-SNAPSHOT"
)
public class SpongePlugin {

    @Inject
    private Logger logger;


    @Listener
    public void onGameStart(GameInitializationEvent e) {
        logger.info("Initializing");

        CommandManager manager = Sponge.getCommandManager();


        final SpongeCommand command = new SpongeCommand();

        manager.register(
                this,
                command.getCommandSpec(),
                "download", "downloadfiles"
        );
    }


}
