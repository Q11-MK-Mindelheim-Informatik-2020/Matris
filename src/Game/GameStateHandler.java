package Game;

import Var.Var;

import java.util.Timer;

public class GameStateHandler {
    public static void changeGameState(String state) {
        Var.gameState = state;
        Var.stopSignal = true;
        switch(state) {
            case "startup":
            case "homescreen":
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "singleplayer":
                Var.timer.cancel();
                Var.timer.purge();
                Var.timer = new Timer();
                Var.timer.schedule(new Mechanics(), Mechanics.getTime());
//                Effects.Sounds.playSound("tetris.wav", 0.5);
                break;
            case "pause":
                Var.timer.cancel();
                Var.timer.purge();
                break;
            case "gameover":
                System.out.println("Ende!");
                break;
        }
        System.out.println("Gamestate: "+state);
    }
}
