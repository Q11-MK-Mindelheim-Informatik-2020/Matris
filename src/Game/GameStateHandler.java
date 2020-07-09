package Game;

import Var.Var;
import javafx.scene.media.MediaPlayer;

import java.util.Timer;

public class GameStateHandler {

    private static MediaPlayer backgroundmusic;

    public static void changeGameState(String state) {
        Var.gameState = state;
        Var.stopSignal = true;
        if (backgroundmusic != null && backgroundmusic.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            backgroundmusic.pause();
        }
        switch(state) {
            case "startup":
            case "homescreen":
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "singleplayer":
                Mechanics.resetTimer();
                Var.timer.schedule(new Mechanics(), Mechanics.getTime());
                if (backgroundmusic == null) {
                    //backgroundmusic = Effects.Sounds.playSound("Worstholdmusicever.mp3", 1.0, true, 10.6, 49);
                    backgroundmusic = Effects.Sounds.playSound("/Tetris NES/2 - Music 2.mp3", 1.0, true);
                }
                else {
                    backgroundmusic.play();
                }
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "pause":
            case "options1":
                Mechanics.resetTimer();
                break;
            case "gameover":
                Mechanics.resetTimer();
                Gui.Gui.resetKeybindingsTimers();
                System.out.println("Ende!");
                break;
        }
        System.out.println("Gamestate: "+state);
    }
}
