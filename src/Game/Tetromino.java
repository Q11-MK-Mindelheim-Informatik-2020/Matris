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
}
