package Game;

import Effects.Sounds;
import Var.Var;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Mechanics extends TimerTask {

    @Override
    public void run() {
        if(!Move.down()) {
            Sounds.playSound("NES - land.mp3", 1.0, false);
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
                        Var.currentBackgroundID = (int) (Math.random()*20);
                        Effects.Sounds.playSound("NES - level up.mp3", 1.0, false);
                        System.out.println("Neues Level: " + Var.level);
                    }
                }
            }

            if (!lines.isEmpty()) {
                try {
                    Move.removeLines(lines);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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
        resetValues();
        Tetromino.spawnRandom();
        GameStateHandler.changeGameState("singleplayer");
    }

    static void resetTimer() {
        try {
            Var.timer.cancel();
            Var.timer.purge();
        }
        catch (IllegalStateException e) {}
        Var.timer = new Timer();
    }

    private static void resetValues() {
        Var.currentBackgroundID = (int) (Math.random()*20);
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

    public static void pause() {
        if (Var.gameState.equals("pause")) {
            GameStateHandler.changeGameState("singleplayer");
        }
        else if (Var.gameState.equals("singleplayer")) {
            GameStateHandler.changeGameState("pause");
        }
    }
}
