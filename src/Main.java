import Game.Box;
import Game.Gravity;
import Game.KeyHandler;
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

        Var.spielfeld[3][6] = new Box(new Color(221, 38, 35),1, false);
        Var.spielfeld[2][6] = new Box(new Color(221, 38, 35),1, false);
        Var.spielfeld[1][6] = new Box(new Color(221, 38, 35),1, false);
        Var.spielfeld[1][5] = new Box(new Color(221, 38, 35), 1, false);

        //Gravitation anmachen
        Gravity grav = new Gravity();
        grav.start();

        DrawArea DA = new DrawArea();

        JFrame jf = new JFrame();
        jf.setSize(800, 1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        jf.setVisible(true);
        jf.add(DA);
        jf.addKeyListener(new KeyHandler());

    }
}
