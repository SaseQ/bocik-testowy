package dev.saseq.assistant.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AssistantConfig {
    private static final Dotenv config = Dotenv.configure().load();

    public AssistantConfig() {
    }

    public static String getProviderUrl() {
        return config.get("MODEL_PROVIDER_URL");
    }

    public static String getModelApiKey() {
        return config.get("MODEL_API_KEY");
    }

    public static String getBraveSearchApiKey() {
        return config.get("BRAVE_SEARCH_API_KEY");
    }
}
