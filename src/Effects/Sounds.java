package Effects;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Sounds {
    //Funktion um Audio abspielen zu lassen
    public static void playSound(String soundFile) {
        InputStream inputStream;
        try {
            //Erstelle Input-Stream
            inputStream = new FileInputStream(new File("rsc/Sounds/" + soundFile));
            AudioStream audio = new AudioStream(inputStream);
            //Spiele Audio ab
            AudioPlayer.player.start(audio);
        }catch(Exception e) {
            System.out.println("Error:" + e);
        }
    }
}
