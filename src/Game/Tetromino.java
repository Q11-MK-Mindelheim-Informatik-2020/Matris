package Game;

import Var.Var;

import java.util.*;


public class Tetromino {
    private static int k = (int) Math.ceil(Var.n/2.0);
    //Für die Namen (Buchstaben) siehe: https://tetris.wiki/Tetromino
    private static void spawnI() {
        Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.imgBlockI);
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockI, true);
        Var.spielfeld[k][Var.tilesY-1] = new Box(Var.imgBlockI);
        Var.spielfeld[k+1][Var.tilesY-1] = new Box(Var.imgBlockI);
    }
    private static void spawnO() {
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockO);
        Var.spielfeld[k][Var.tilesY-1] = new Box(Var.imgBlockO);
        Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.imgBlockO);
        Var.spielfeld[k][Var.tilesY-2] = new Box(Var.imgBlockO);
    }
    private static void spawnT() {
        Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.imgBlockT);
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockT, true);
        Var.spielfeld[k][Var.tilesY-1] = new Box(Var.imgBlockT);
        Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.imgBlockT);
    }
    private static void spawnS() {
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockS, true);
        Var.spielfeld[k][Var.tilesY-1] = new Box(Var.imgBlockS);
        Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.imgBlockS);
        Var.spielfeld[k-2][Var.tilesY-2] = new Box(Var.imgBlockS);
    }
    private static void spawnZ() {
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockZ, true);
        Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.imgBlockZ);
        Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.imgBlockZ);
        Var.spielfeld[k][Var.tilesY-2] = new Box(Var.imgBlockZ);
    }
    private static void spawnJ() {
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockJ, true);
        Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.imgBlockJ);
        Var.spielfeld[k][Var.tilesY-1] = new Box(Var.imgBlockJ);
        Var.spielfeld[k][Var.tilesY-2] = new Box(Var.imgBlockJ);
    }
    private static void spawnL() {
        Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.imgBlockL, true);
        Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.imgBlockL);
        Var.spielfeld[k][Var.tilesY-1] = new Box(Var.imgBlockL);
        Var.spielfeld[k-2][Var.tilesY-2] = new Box(Var.imgBlockL);
    }
    public static void spawnRandom() {
        if (Var.bag.isEmpty()) {
            Var.bag = new LinkedList<>(Arrays.asList('I','O','T','S','Z','J','L'));
            Collections.shuffle(Var.bag);
        }
        if(Var.bag.size() <= 7) {
            List<Character> bag2 = new LinkedList<>(Arrays.asList('I','O','T','S','Z','J','L'));
            Collections.shuffle(bag2);
            Var.bag.addAll(bag2);
        }
        switch (Var.bag.get(0)) {
            case 'I':
                spawnI();
                break;
            case 'O':
                spawnO();
                break;
            case 'T':
                spawnT();
                break;
            case 'S':
                spawnS();
                break;
            case 'Z':
                spawnZ();
                break;
            case 'J':
                spawnJ();
                break;
            case 'L':
                spawnL();
                break;
        }
        Var.bag.remove(0);
    }
}
