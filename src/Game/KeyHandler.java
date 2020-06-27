package Game;

import Var.Var;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class KeyHandler implements KeyListener {

    private int lastkey = -1;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                //es wird versucht den Spielstein mit currentid nach rechts zu verschieben
                //Move.right() liefert einen Boolean wert zurück: true wenn erfolgreich, false falls irgendwas dazwischen ist
                if (Var.gameState.equals("singleplayer")) {
                    Move.right();
                }
                break;
            case KeyEvent.VK_LEFT:
                //siehe oben
                if (Var.gameState.equals("singleplayer")) {
                    Move.left();
                }
                break;
            case KeyEvent.VK_UP:
                if (Var.gameState.equals("singleplayer")) {
                    Move.downdown();
                }
                //Move.up();
                break;
            case KeyEvent.VK_DOWN:
                if (Var.gameState.equals("singleplayer")) {
                    if((e.getModifiers() & KeyEvent.VK_C) != 0) { //mit c + unten kann gecheatet werden ;)
                        Move.up();
                    }
                    else {
                        Move.down();
                    }
                }
                break;
            case KeyEvent.VK_ENTER:
                if (Var.gameState.equals("singleplayer") && e.getKeyCode() != lastkey) {
                    lastkey = e.getKeyCode();
                    Move.rotate();
                }
                break;
            case KeyEvent.VK_0:
                GameStateHandler.changeGameState("homescreen");
                break;
            case KeyEvent.VK_1:
                GameStateHandler.changeGameState("singleplayer");
                break;
            case KeyEvent.VK_CONTROL:
                if (Var.gameState.equals("singleplayer") && !Var.stored) {
                    Var.timer.cancel();
                    Var.timer.purge();
                    char shape = Var.currentTetrominoshape;
                    Move.removeTetromino();
                    if (Var.storedTetromino == 'x') {
                        Tetromino.spawnRandom();
                    }
                    else {
                        Tetromino.spawn(Var.storedTetromino);
                    }
                    Var.storedTetromino = shape;
                    Var.timer = new Timer();
                    Var.timer.schedule(new Mechanics(), Mechanics.getTime());
                    Var.stored = true;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (Var.gameState.equals("pause")) {
                    GameStateHandler.changeGameState("singleplayer");
                }
                else if (Var.gameState.equals("singleplayer")) {
                    GameStateHandler.changeGameState("pause");
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        lastkey = -1;
    }
}
