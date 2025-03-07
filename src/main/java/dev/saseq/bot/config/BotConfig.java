package dev.saseq.bot.config;

import io.github.cdimascio.dotenv.Dotenv;

public class BotConfig {
    private static final Dotenv config = Dotenv.configure().load();

    private BotConfig() {
    }

    public static String getToken() {
        return config.get("TOKEN");
    }

    public static String getDevServerID() {
        return config.get("DEV_SERVER_ID");
    }

    public static String getOwnerID() {
        return config.get("OWNER_ID");
    }
}
