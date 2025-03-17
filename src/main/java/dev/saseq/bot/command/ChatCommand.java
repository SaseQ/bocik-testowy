package dev.saseq.bot.command;

import dev.saseq.assistant.Assistant;
import dev.saseq.assistant.service.AssistantService;
import dev.saseq.command.impl.SlashCommand;
import lombok.NonNull;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class ChatCommand extends SlashCommand {
    private final AssistantService assistantService;

    public ChatCommand() {
        this.name = "chat";
        this.description = "Zapytaj asystenta AI";
        this.options = List.of(
                new OptionData(
                        OptionType.STRING,
                        "content",
                        "O co chcesz zapytać asystenta AI?",
                        true
                )
        );

        this.assistantService = new AssistantService();
    }

    @Override
    public void execute(@NonNull SlashCommandInteractionEvent event) {
        OptionMapping contentOption = event.getOption("content");
        if (contentOption == null) return;
        String content = contentOption.getAsString();

        Assistant assistant = assistantService.getAssistant();

        event.deferReply().queue(hook -> {
            StringBuilder responseBuffer = new StringBuilder();
            assistant.chat(content)
                    .onPartialResponse(token -> {
                        responseBuffer.append(token);
                        if (responseBuffer.length() % 3 == 0) {
                            hook.editOriginal(responseBuffer.toString()).queue();
                        }
                    })
                    .onCompleteResponse(response ->
                            hook.editOriginal(responseBuffer.toString()).queue())
                    .onError(error ->
                            hook.editOriginal("Error: " + error.getMessage()).queue())
                    .start();
        });
    }
}
