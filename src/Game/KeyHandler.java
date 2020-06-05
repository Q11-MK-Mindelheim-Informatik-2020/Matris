package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    int lastkey = -1;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                //es wird versucht den Spielstein mit currentid nach rechts zu verschieben
                //Move.right() liefert einen Boolean wert zurück: true wenn erfolgreich, false falls irgendwas dazwischen ist
                Move.right();
                break;
            case KeyEvent.VK_LEFT:
                //siehe oben
                Move.left();
                break;
            case KeyEvent.VK_UP:
                Move.downdown();
                //Move.up();
                break;
            case KeyEvent.VK_DOWN:
                if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) { //mit strg + unten kann gecheatet werden ;)
                    Move.up();
                }
                else {
                    Move.down();
                }
                break;
            case KeyEvent.VK_ENTER:
                if (e.getKeyCode() != lastkey) {
                    lastkey = e.getKeyCode();
                    Move.rotate();
                }
                break;
            case KeyEvent.VK_0:
                System.out.println("state 0 (startup)");
                GameStateHandler.changeGameState("startup");
                break;
            case KeyEvent.VK_1:
                System.out.println("state 1 (menu)");
                GameStateHandler.changeGameState("menu");
                break;
            case KeyEvent.VK_M:

                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        lastkey = -1;
    }
}
