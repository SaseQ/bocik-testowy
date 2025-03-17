package dev.saseq.bot;

import dev.saseq.bot.command.ChatCommand;
import dev.saseq.bot.command.PingCommand;
import dev.saseq.bot.config.BotConfig;
import dev.saseq.command.managers.CommandManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
@Slf4j
public enum Bot {
    INSTANCE;

    @Getter
    private JDA jda;
    private CommandManager manager;

    public void initialize() {
        if (this.jda == null) {
            try {
                jda = JDABuilder.createDefault(BotConfig.getToken())
                        .setAutoReconnect(true)
                        .build();

                initCommandManager();
                initCommands();
                initListeners();

                log.info("JDA initialized successfully!");
            } catch (Exception e) {
                log.error("Failed to initialize JDA", e);
            }
        }
    }

    private void initCommandManager() {
        manager = new CommandManager();
        manager.setOwnerId(BotConfig.getOwnerID());
        manager.setDevGuildId(BotConfig.getDevServerID());
    }

    private void initCommands() {
        manager.addCommand(new PingCommand());
        manager.addCommand(new ChatCommand());
        log.info("Commands initialized!");
    }

    private void initListeners() {
        jda.addEventListener(manager);
        log.info("Listeners initialized!");
    }
}
