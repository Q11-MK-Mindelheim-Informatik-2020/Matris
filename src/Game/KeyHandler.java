package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                System.out.println("Rechts");
                //es wird versucht den Spielstein mit currentid nach rechts zu verschieben
                //Move.right() liefert einen Boolean wert zurück: true wenn erfolgreich, false falls irgendwas dazwischen ist
                Move.right();
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Links");
                //siehe oben
                Move.left();
                break;
            case KeyEvent.VK_UP:
                System.out.println("Hoch");
                Move.downdown();
                //Move.up();
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Runter");
                //siehe oben
                Move.down();
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Rotieren");
                Move.rotate();
                break;
            case KeyEvent.VK_0:
                System.out.println("state 0 (startup)");
                Game.GameStateHandler.changeGameState("startup");
                break;
            case KeyEvent.VK_1:
                System.out.println("state 1 (menu)");
                Game.GameStateHandler.changeGameState("menu");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
