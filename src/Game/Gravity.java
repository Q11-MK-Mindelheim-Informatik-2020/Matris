package Game;

import Var.Var;

public class Gravity extends Thread {
    public void run() {
        Tetromino.spawnRandom();
        while (true)  {
            //jede sekunde wird versucht den stein mit der currentid nachunten zu bewegen
            sleep(1000); //verzögerung
            if(!Move.down()) {
                sleep(1000);
                Var.currentid++; //erhöhen der currentid
                Tetromino.spawnRandom(); //neuen stein mit der aktuellen id spawnen
            }
        }
    }
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
