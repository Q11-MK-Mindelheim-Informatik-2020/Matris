package Game;

import Var.Var;

import java.util.*;


public class Tetromino {
    //Für die Namen (Buchstaben) siehe: https://tetris.wiki/Tetromino
    static void spawn(char c) {
        Var.currentTetrominoshape = c;
        if (c == 'O') {
            setBlocks(Var.Tetrominos.get(c), c, false);
            // Bei einem O Tetromino wird die Rotation gesperrt
        }
        else {
            setBlocks(Var.Tetrominos.get(c), c, true);
        }
    }

    public static void spawnRandom() {
        //Wenn das Array leer ist, werden die die 7 Tetrominos in zufälliger Reihenfolge hinzugefügt
        if (Var.bag.isEmpty()) {
            Var.bag = new LinkedList<>(Arrays.asList('I','O','T','S','Z','J','L'));
            Collections.shuffle(Var.bag);
        }
        // Wenn die Tetrominoanzahl kleiner als 8 ist, werden 7 weitere verschiedene Terominos in zufälliger
        // Reihenfolge hinzugefügt
        if(Var.bag.size() <= 7) {
            List<Character> bag2 = new LinkedList<>(Arrays.asList('I','O','T','S','Z','J','L'));
            Collections.shuffle(bag2);
            Var.bag.addAll(bag2);
        }
        //Das Tetromino an der Position 0 wird erschaffen und anschließend gelöscht
        spawn(Var.bag.get(0));
        Var.bag.remove(0);
    }

    /**
     * Es wird überprüft, ob der Stein setzbar ist
     * Falls nicht ist das Spiel "Gameover"
     */
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
