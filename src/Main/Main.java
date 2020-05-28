package Main;

import Game.Box;
import Game.Mechanics;
import Gui.Gui;
import Var.Var;

import java.awt.*;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Var v = new Var();
        for(int i = 0; i < Var.n; i++) {
            for(int j = 0; j < Var.m; j++) {
                Var.spielfeld[i][j] = new Box(null, 0);
            }
        }
        Mechanics grav = new Mechanics();
        grav.start();
        Game.GameStateHandler.changeGameState("startup");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui frame = new Gui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Timer timer = new Timer();
    }
}
