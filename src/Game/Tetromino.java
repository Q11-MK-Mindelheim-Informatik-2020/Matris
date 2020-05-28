package Game;

import Var.Var;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tetromino {
    static int k = (int) Math.ceil(Var.n/2.0);
    //Für die Namen (Buchstaben) siehe: https://tetris.wiki/Tetromino
    public static void spawnI(BufferedImage c) {
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k+1][Var.m-1] = new Box(c);
    }
    public static void spawnO(BufferedImage c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
        Var.spielfeld[k][Var.m-2] = new Box(c);
    }
    public static void spawnT(BufferedImage c) {
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
    }
    public static void spawnS(BufferedImage c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
        Var.spielfeld[k-2][Var.m-2] = new Box(c);
    }
    public static void spawnZ(BufferedImage c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k-1][Var.m-2] = new Box(c);
        Var.spielfeld[k][Var.m-2] = new Box(c);
    }
    public static void spawnJ(BufferedImage c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-2] = new Box(c);
    }
    public static void spawnL(BufferedImage c) {
        Var.spielfeld[k-1][Var.m-1] = new Box(c, true);
        Var.spielfeld[k-2][Var.m-1] = new Box(c);
        Var.spielfeld[k][Var.m-1] = new Box(c);
        Var.spielfeld[k-2][Var.m-2] = new Box(c);
    }
    public static void spawnRandom() {
        int random = (int) (7*Math.random());
        switch (random) {
            case 0:
                spawnI((BufferedImage)Var.imgBlockI);
                break;
            case 1:
                spawnO((BufferedImage)Var.imgBlockO);
                break;
            case 2:
                spawnT((BufferedImage)Var.imgBlockT);
                break;
            case 3:
                spawnS((BufferedImage)Var.imgBlockS);
                break;
            case 4:
                spawnZ((BufferedImage)Var.imgBlockZ);
                break;
            case 5:
                spawnJ((BufferedImage)Var.imgBlockJ);
                break;
            case 6:
                spawnL((BufferedImage)Var.imgBlockL);
                break;
        }
    }
}
