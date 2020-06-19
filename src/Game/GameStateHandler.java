package Game;

import Var.Var;

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
//                Effects.Sounds.playSound("tetris99.wav", 0.5);
                break;
        }
        System.out.println("Gamestate: "+state);
    }
}
