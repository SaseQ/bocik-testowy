package dev.saseq.assistant.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;
import dev.saseq.assistant.Assistant;
import dev.saseq.assistant.config.AssistantConfig;

public class AssistantService {
    private static final String MODEL_NAME = "google/gemini-2.0-flash-lite-preview-02-05:free";

    private final OpenAiStreamingChatModel model;
    private final MessageWindowChatMemory chatMemory;
    private final ToolProvider toolProvider;

    public AssistantService() {
        this.model = buildAssistantModel();
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(25);
        this.toolProvider = new McpService().getToolProvider();
    }

    private OpenAiStreamingChatModel buildAssistantModel() {
        return OpenAiStreamingChatModel.builder()
                .baseUrl(AssistantConfig.getProviderUrl())
                .apiKey(AssistantConfig.getModelApiKey())
                .modelName(MODEL_NAME)
                .build();
    }

    public Assistant getAssistant() {
        return AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(model)
                .chatMemory(chatMemory)
                .toolProvider(toolProvider)
                .build();
    }
}
