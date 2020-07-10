package Game;

import Var.Var;
import javafx.scene.media.MediaPlayer;

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
                Var.spielfeld = new Box[Var.n][Var.m];
                for(int i = 0; i < Var.n; i++) {
                    for(int j = 0; j < Var.m; j++) {
                        Var.spielfeld[i][j] = new Box(0);
                    }
                }
                Var.updateTetrominos();
                Game.Tetromino.spawnRandom();
                break;
            case "homescreen":
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "singleplayer":
                Mechanics.resetTimer();
                Var.timer.schedule(new Mechanics(), Mechanics.getTime());
                if (backgroundmusic == null) {
                    //backgroundmusic = Effects.Sounds.playSound("Worstholdmusicever.mp3", 1.0, true, 10.6, 49);
                    backgroundmusic = Effects.Sounds.playSound("Gameboy - Tetris Theme.mp3", 0.6, true);
                }
                else {
                    backgroundmusic.play();
                }
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "pause":
            case "controls":
            case "options1":
                Mechanics.resetTimer();
                Effects.Sounds.playSound("NES - pause.mp3", 1.0, false);
                break;
            case "gameover":
                Mechanics.resetTimer();
                Gui.Gui.resetKeybindingsTimers();
                Effects.Sounds.playSound("NES - gameover.mp3", 1.0, false);
                System.out.println("Ende!");
                break;
        }
        System.out.println("Gamestate: "+state);
    }
}
