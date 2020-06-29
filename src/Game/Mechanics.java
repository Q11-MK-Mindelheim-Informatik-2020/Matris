package Game;

import Var.Var;

import java.util.ArrayList;
import java.util.Timer;
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
                    if (Var.linecounter%10 == 0) {
                        Var.level++;
                        System.out.println("Neues Level: " + Var.level);
                    }
                }
            }

            Move.removeLines(lines);

            //System.out.println("Folgende Linien sind fertig: " + lines);
            //System.out.println("Linien: " + Var.linecounter);

            Var.currentid++; //erhöhen der currentid
            Tetromino.spawnRandom(); //neuen stein mit der aktuellen id spawnen
            Var.stored = false;
        }

        Var.timer.schedule(new Mechanics(),getTime());

    }

    static int getTime() {
        return (int) (50/3.0 * (1410.7/(Math.pow(Var.level, 2.34)+30) + 1));
    }

    public static void restartGame() {
        reset();
        Tetromino.spawnRandom();
        GameStateHandler.changeGameState("singleplayer");
    }
    static void reset() {
        Var.timer.cancel();
        Var.timer.purge();
        Var.timer = new Timer();
        Var.level = 0;
        Var.score = 0;
        Var.linecounter = 0;
        Var.bag.clear();
        Var.currentid = 1;
        Var.stored = false;
        Var.storedTetromino = 'x';
        Var.currentTetrominoshape = '\0';
        for(int i = 0; i < Var.n; i++) {
            for(int j = 0; j < Var.m; j++) {
                Var.spielfeld[i][j] = new Box(0);
            }
        }
    }
}
