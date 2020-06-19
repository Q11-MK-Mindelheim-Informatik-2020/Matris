package Game;

import Var.Var;

import java.util.Timer;

public class GameStateHandler {
    public static void changeGameState(String state) {
        Var.gameState = state;
        Var.stopSignal = true;
        switch(state) {
            case "startup":
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "homescreen":
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "singleplayer":
                if(Var.currentid == 1) {
                    Game.Tetromino.spawnRandom();
                }
                Var.timer = new Timer();
                Var.timer.schedule(new Mechanics(), 1000);
                Effects.Sounds.playSound("tetris99.wav", 0.5);
                break;
            case "pause":
                Var.timer.cancel();
                Var.timer.purge();
                break;
        }
        System.out.println("Gamestate: "+state);
    }
}
