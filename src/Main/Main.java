package Main;

import Gui.Gui;
import Var.Var;

import java.awt.*;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Var v = new Var();
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
        //Effects.Sounds.playSound("tetris99.wav", 1.022, 278.73, 0.01);
        //Effects.Sounds.playSound("tetris99.wav", 1.022, 14.95, 0.05);
        Timer timer = new Timer();
    }
}
