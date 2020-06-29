package Game;

import Var.Var;

import java.util.ArrayList;
import java.util.Timer;

class Move {

    static boolean right() {
        return move(1,0);
    }
    static boolean left() {
        return move(-1,0);
    }
    static boolean down() {
        return move(0,-1);
    }
    static boolean up() {
        return move(0,1);
    }
    static void downdown() {
        int y;
        if (Var.ghostmode) {
            y = Var.m-1;
            while (!move(0,-y)) {
                y--;
            }
        }
        else {
            y = 0;
            while (move(0,-y,false)) {
                y++;
            }
        }
        move(0,-y+1);

        Var.timer.cancel();
        Var.timer.purge();
        Var.timer = new Timer();
        Var.timer.schedule(new Mechanics(), 0);
    }

    private static boolean move(int x, int y) {
        return move(x,y,true);
    }

    private static boolean move(int x, int y, boolean handle) { //Stein mit currentid wird versucht zu verschieben
        boolean bool = true; //wird auf false gesetzt, falls move nicht erfolgreich
        Box[][] tempspielfeld = new Box[Var.n][Var.m]; //zwischenspielfeld

        if(handle) {
            for (int i = 0; i < Var.n; i++) {
                for (int j = 0; j < Var.m; j++) {
                    if(Var.spielfeld[i][j].getId() != Var.currentid && Var.spielfeld[i][j].getId() != 0) {
                        tempspielfeld[i][j] = Var.spielfeld[i][j];
                    }
                    else {
                        tempspielfeld[i][j] = new Box(null, 0);
                    }
                }
            }
        }

        loop:
        for(int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    //System.out.println(i + "|" + j);
                    if(i+x < 0 || j+y < 0 || i+x >= Var.n || j+y >= Var.m || (Var.spielfeld[i+x][j+y].getId() != 0 && Var.spielfeld[i+x][j+y].getId() != Var.currentid)) { //wird geschaut ob es irgendwo an der wand ist oder ein stein im weg ist
                        //System.out.println("Kann nicht bewegt werden!");
                        bool = false;
                        break loop;
                    }
                    else if(handle) {
                        //System.out.println(i + "|" + j + " wird verschoben");
                        tempspielfeld[i+x][j+y] = Var.spielfeld[i][j];
                    }
                }
            }
        }
        if(bool && handle) {
            for (int i = 0; i < Var.n; i++) {
                if (Var.m >= 0) System.arraycopy(tempspielfeld[i], 0, Var.spielfeld[i], 0, Var.m);
            }
            showPreview();
        }
        return bool;
    }

    static boolean rotate() {
        boolean bool = true; //wird auf false gesetzt, falls move nicht erfolgreich

        int a = 0;
        int b = 0;

        Box[][] tempspielfeld = new Box[Var.n][Var.m]; //zwischenspielfeld

        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() != Var.currentid && Var.spielfeld[i][j].getId() != 0) {
                    tempspielfeld[i][j] = Var.spielfeld[i][j];
                }
                else {
                    if(Var.spielfeld[i][j].getRotationpoint()) {
                        a = i;
                        b = j;
                    }
                    tempspielfeld[i][j] = new Box(null, 0, false);
                }
            }
        }

        loop:
        for(int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    if(a-b+j < 0 || a+b-i < 0 || a-b+j >= Var.n || a+b-i >= Var.m || (Var.spielfeld[a-b+j][a+b-i].getId() != 0 && Var.spielfeld[a-b+j][a+b-i].getId() != Var.currentid)) { //wird geschaut ob es irgendwo an der wand ist oder beim drehen ein stein im weg ist
                        //System.out.println("Kann nicht bewegt werden!");
                        bool = false;
                        break loop;
                    }
                    else {
                        //System.out.println(i + "|" + j + " wird gedreht");
                        tempspielfeld[a-b+j][a+b-i] = Var.spielfeld[i][j];
                    }
                }
            }
        }
        if(bool) {
            for (int i = 0; i < Var.n; i++) {
                if (Var.m >= 0) System.arraycopy(tempspielfeld[i], 0, Var.spielfeld[i], 0, Var.m);
            }
            showPreview();
        }
        return bool;
    }

    static void removeLines(ArrayList<Integer> lines) {
        //Linien löschen
        for (int j : lines) {
            for (int i = 0; i < Var.n; i++) {
                Var.spielfeld[i][j] = new Box(null, 0);
            }
        }
        //linien runtermoven
        for (int j = 1; j < Var.m; j++) {
            int z = 0;
            for (int k : lines) {
                if (j > k) {
                    z++;
                }
                else {
                    break;
                }
            }
            if(z !=0) {
                for (int i = 0; i < Var.n; i++) {
                    Var.spielfeld[i][j-z] = Var.spielfeld[i][j];
                    Var.spielfeld[i][j] = new Box(null,0);
                }
            }
        }
        int l = lines.size();
        Var.score += (Var.level+1)*(5*l*(l*(l*(11*l - 54) + 91) - 24))/3; //Polynom vierten Grades in Horner Form, das die Punkte für l linien berechnet. Siehe https://tetris.wiki/Scoring#Original_Nintendo_scoring_system
        System.out.println("Score: " + Var.score);
    }

    static void removeTetromino() {
        int c = 0;
        loop:
        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    Var.spielfeld[i][j] = new Box(null,0);
                    c++;
                    if(c == 4) {
                        break loop;
                    }
                }
            }
        }
    }

    static void showPreview() {
        int y;
        if (Var.ghostmode) {
            y = Var.m-1;
            while (!move(0,-y, false) && y < Var.m) {
                y--;
            }
        }
        else {
            y = 0;
            while (move(0,-y,false) && y < Var.m) {
                y++;
            }
            y--;
        }

        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                //System.out.println(y);
                if(Var.spielfeld[i][j].getId() == Var.currentid && Var.spielfeld[i][j-y].getId() == 0) {
                    Var.spielfeld[i][j-y] = new Box(Var.images.get("Marker"), 0);
                }
                else if (Var.spielfeld[i][j].getId() == 0 && Var.spielfeld[i][j].getTileTexture() != null) {
                    Var.spielfeld[i][j] = new Box(null, 0);
                }

            }
        }
    }
}