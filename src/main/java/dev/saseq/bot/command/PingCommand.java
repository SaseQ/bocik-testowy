package dev.saseq.bot.command;

import dev.saseq.command.impl.SlashCommand;
import lombok.NonNull;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand extends SlashCommand {
    public PingCommand() {
        this.name = "ping";
        this.description = "Performs a ping to see the bot's delay";
        this.permissions = new Permission[]{ Permission.ADMINISTRATOR };
    }

    @Override
    public void execute(@NonNull SlashCommandInteractionEvent event) {
        event.reply("Ping: " + event.getJDA().getGatewayPing() + "ms").queue();
    }
}
