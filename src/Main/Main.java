package Main;

import Game.Box;
import Game.Mechanics;
import Gui.Gui;
import Var.Var;
import javafx.embed.swing.JFXPanel;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        new Var();
        new JFXPanel();
        for(int i = 0; i < Var.n; i++) {
            for(int j = 0; j < Var.m; j++) {
                Var.spielfeld[i][j] = new Box(0);
            }
        }

        Game.Tetromino.spawnRandom();

        Game.GameStateHandler.changeGameState("options1");
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
