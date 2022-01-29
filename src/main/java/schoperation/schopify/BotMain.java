package schoperation.schopify;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
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
        //DiscordClient client = DiscordClient.create(getTokenFromFile());

        for (final Mixer.Info info : AudioSystem.getMixerInfo())
            System.out.format("%s %s %s %s%n", info.getName(), info.getVendor(), info.getVersion(), info.getDescription());

        System.out.println(AudioSystem.getMixerInfo().length);

        AudioPlayerManager manager = new DefaultAudioPlayerManager();
        manager.getConfiguration().setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
        AudioSourceManagers.registerLocalSource(manager);
        AudioSourceManagers.registerRemoteSources(manager);


        manager.createPlayer();

        /*
        // Handle certain events
        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(ReadyEvent.class, event ->
                        Mono.fromRunnable(() -> {
                            final User self = event.getSelf();
                            System.out.println("Logged in as " + self.getUsername() + "#" + self.getDiscriminator());
                        })));
        login.block();*/
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
