package Main;

import Gui.Gui;
import Var.Var;

import java.awt.*;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Var v = new Var();
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
        Effects.Sounds.playSound("tetris99.wav", 1.022, 278.73, 0.5);
        Timer timer = new Timer();
    }
}
