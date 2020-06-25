package Game;

import Var.Var;

import java.awt.image.BufferedImage;
import java.util.*;


public class Tetromino {
    private static int k = (int) Math.ceil(Var.n/2.0);
    //Für die Namen (Buchstaben) siehe: https://tetris.wiki/Tetromino
    static void spawn(char c) {
        Var.currentTetrominoshape = c;

        switch (c) {
            case 'I':
                Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)), true);
                Var.spielfeld[k][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k+1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                break;
            case 'O':
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                break;
            case 'T':
                Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)), true);
                Var.spielfeld[k][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                break;
            case 'S':
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)), true);
                Var.spielfeld[k][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-2][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                break;
            case 'Z':
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)), true);
                Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-1][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                break;
            case 'J':
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)), true);
                Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                break;
            case 'L':
                Var.spielfeld[k-1][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)), true);
                Var.spielfeld[k-2][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k][Var.tilesY-1] = new Box(Var.images.get(String.valueOf(c)));
                Var.spielfeld[k-2][Var.tilesY-2] = new Box(Var.images.get(String.valueOf(c)));
                break;
            default:
                System.out.println("Ungültiger Tetromino");
        }

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
        spawn(Var.bag.get(0));
        Var.bag.remove(0);
    }
}
