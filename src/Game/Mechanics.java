package Game;

import Var.Var;

import java.awt.*;
import java.util.ArrayList;

public class Mechanics extends Thread {
    public void run() {
        Tetromino.spawnRandom();
        while (true)  {
            //jede sekunde wird versucht den stein mit der currentid nachunten zu bewegen
            sleep(1000); //verzögerung
            if(!Move.down()) {

                ArrayList<Integer> lines = new ArrayList<Integer>();

                for (int j = 0; j < Var.m; j++) {
                    boolean bool = true;
                    for (int i = 0; i < Var.n; i++) {
                        if(Var.spielfeld[i][j].getId() == 0) {
                            bool = false;
                            break;
                        }
                    }
                    if (bool) {
                        lines.add(j);
                        Var.linecounter++;
                    }
                }

                Move.removeLines(lines);

                System.out.println("Folgende Linien sind fertig: " + lines);
                System.out.println("Linien: " + Var.linecounter);

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
