package schoperation.schopify;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    Main class that initializes the bot.
*/
public class BotMain
{
    public static void main(String args[])
    {
        DiscordClient client = DiscordClient.create(getTokenFromFile());

        // Handle certain events
        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(ReadyEvent.class, event ->
                        Mono.fromRunnable(() -> {
                            final User self = event.getSelf();
                            System.out.println("Logged in as " + self.getUsername() + "#" + self.getDiscriminator());
                        })));
        login.block();
    }

    // Grabs the token from a text file. Hidden on the GitHub.
    private static String getTokenFromFile()
    {
        try
        {
            File tokenFile = new File("token.txt");
            Scanner fileReader = new Scanner(tokenFile);

            if (fileReader.hasNextLine())
                return fileReader.nextLine();

            fileReader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return "no token lol";
    }
}
