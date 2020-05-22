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
        Effects.Sounds.playSound("tetris.wav");
        Timer timer = new Timer();
    }
}
