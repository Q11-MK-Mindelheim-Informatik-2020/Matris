import Game.Box;
import Game.Mechanics;
import Game.KeyHandler;
import Gui.DrawArea;
import Var.Var;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        //Initialisieren des Spielfeldes
        for(int i = 0; i < Var.n; i++) {
            for(int j = 0; j < Var.m; j++) {
                Var.spielfeld[i][j] = new Box(new Color(0,0,0), 0, false);
            }
        }
        Effects.Sounds.playSound("tetris99.wav", 1.022, 278.73, 0.5);

        /*
        Var.spielfeld[3][6] = new Box(new Color(221, 38, 35),1, true);
        Var.spielfeld[2][6] = new Box(new Color(221, 38, 35),1);
        Var.spielfeld[1][6] = new Box(new Color(221, 38, 35),1);
        Var.spielfeld[4][6] = new Box(new Color(221, 38, 35),1);
        Var.spielfeld[1][5] = new Box(new Color(221, 38, 35), 1);
        Var.spielfeld[5][7] = new Box(new Color(221, 38, 35), 1);
        Var.spielfeld[5][6] = new Box(new Color(221, 38, 35), 1);
        Var.spielfeld[5][8] = new Box(new Color(221, 38, 35), 1);
        Var.spielfeld[1][4] = new Box(new Color(221, 38, 35), 1);
        */

        //Tetromino.spawnT(new Color(249, 13, 255));

        //Gravitation anmachen
        Mechanics grav = new Mechanics();
        grav.start();

        Gui.DrawArea DA = new DrawArea();

        JFrame jf = new JFrame();
        jf.setSize(800, 1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        jf.setVisible(true);
        jf.add(DA);
        jf.addKeyListener(new KeyHandler());

    }
}
//hallo 123