package Main;

import Game.Box;
import Game.Mechanics;
import Gui.Gui;
import Var.Var;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        new Var();
        for(int i = 0; i < Var.n; i++) {
            for(int j = 0; j < Var.m; j++) {
                Var.spielfeld[i][j] = new Box(0);
            }
        }

        Game.Tetromino.spawnRandom();

        Game.GameStateHandler.changeGameState("singleplayer");
        EventQueue.invokeLater(() -> {
            try {
                Gui frame = new Gui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
