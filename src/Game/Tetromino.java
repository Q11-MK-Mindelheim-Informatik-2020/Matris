package Game;

import Var.Var;

import java.util.*;


public class Tetromino {
    //Für die Namen (Buchstaben) siehe: https://tetris.wiki/Tetromino
    static void spawn(char c) {
        Var.currentTetrominoshape = c;
        if (c == 'O') {
            setBlocks(Var.Tetrominos.get(c), c, false);
        }
        else {
            setBlocks(Var.Tetrominos.get(c), c, true);
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

    private static void setBlocks(int[][] blocks, char c, boolean bool) {
        for (int i = 0; i < 4; i++) {
            if (Var.spielfeld[blocks[i][0]][blocks[i][1]].getId() == 0) {
                Var.spielfeld[blocks[i][0]][blocks[i][1]] = new Box(Var.images.get(String.valueOf(c)), bool);
            }
            else {
                GameStateHandler.changeGameState("gameover");
                break;
            }
            bool = false;
        }
    }
}
