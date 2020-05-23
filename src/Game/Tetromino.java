package Game;

import Var.Var;

import java.awt.*;

public class Tetromino {
    static int k = (int) Math.ceil(Var.n/2.0);
    //Für die Namen (Buchstaben) siehe: https://tetris.wiki/Tetromino
    public static void spawnI(Color c) {
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k+1][Var.m-1] = new Box(c);
    }
    public static void spawnO(Color c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
        Var.spielfeld[k][Var.m-2] = new Box(c);
    }
    public static void spawnT(Color c) {
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
    }
    public static void spawnS(Color c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
        Var.spielfeld[k-2][Var.m-2] = new Box(c);
    }
    public static void spawnZ(Color c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
        Var.spielfeld[k][Var.m-2] = new Box(c);
    }
    public static void spawnJ(Color c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-2] = new Box(c);
    }
    public static void spawnL(Color c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-2][Var.m-2] = new Box(c);
    }
    public static void spawnRandom() {
        int random = (int) (7*Math.random());
        switch (random) {
            case 0:
                spawnI(new Color(15, 223, 255));
                break;
            case 1:
                spawnO(new Color(249, 13, 255));
                break;
            case 2:
                spawnT(new Color(69, 255, 81));
                break;
            case 3:
                spawnS(new Color(179, 221,0));
                break;
            case 4:
                spawnZ(new Color(153, 133, 255));
                break;
            case 5:
                spawnJ(new Color(221, 38, 35));
                break;
            case 6:
                spawnL(new Color(2,0, 221));
                break;
        }
    }
}
