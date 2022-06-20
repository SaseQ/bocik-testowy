package bociktestowy;

import bociktestowy.commands.HelloCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    private static final String TOKEN = "OTQyMDUzMDg0NDA0NzkzNDI1.Yge5TQ.Do-YRGDRl9L4SewNRAD_tjYsQyc";

    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault(TOKEN).build();

        jda.addEventListener(new HelloCommand());
    }
}
