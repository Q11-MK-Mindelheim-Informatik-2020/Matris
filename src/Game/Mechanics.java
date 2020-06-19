package Game;

import Var.Var;

import java.util.ArrayList;
import java.util.TimerTask;

public class Mechanics extends TimerTask {

    @Override
    public void run() {
        if(!Move.down()) {
            ArrayList<Integer> lines = new ArrayList<>();

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

            //System.out.println("Folgende Linien sind fertig: " + lines);
            //System.out.println("Linien: " + Var.linecounter);

            Var.currentid++; //erhöhen der currentid
            Tetromino.spawnRandom(); //neuen stein mit der aktuellen id spawnen
        }

        Var.timer.schedule(new Mechanics(),1000);

    }
}
