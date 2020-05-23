package Game;

import Var.Var;

import java.awt.*;

public class Move {

    public static boolean right() {
        return move(1,0);
    }
    public static boolean left() {
        return move(-1,0);
    }
    public static boolean down() {
        return move(0,-1);
    }
    public static boolean up() {
        return move(0,1);
    }

    private static boolean move(int x, int y) { //Stein mit currentid wird versucht zu verschieben
        boolean bool = true; //wird auf false gesetzt, falls move nicht erfolgreich

        Box[][] tempspielfeld = new Box[Var.n][Var.m]; //zwischenspielfeld

        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() != Var.currentid) {
                    tempspielfeld[i][j] = Var.spielfeld[i][j];
                }
                else {
                    tempspielfeld[i][j] = new Box(new Color(0,0,0), 0, false);
                }
            }
        }

        loop:
        for(int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    if(i+x < 0 || j+y < 0 || i+x >= Var.n || j+y >= Var.m) { //wird geschaut ob es irgendwo an der wand ist
                        System.out.println("Kann nicht bewegt werden!");
                        bool = false;
                        break loop;
                    }
                    else {
                        System.out.println(i + "|" + j + " wird verschoben");
                        tempspielfeld[i+x][j+y] = Var.spielfeld[i][j];
                    }
                }
            }
        }
        if(bool) {
            for (int i = 0; i < Var.n; i++) {
                if (Var.m >= 0) System.arraycopy(tempspielfeld[i], 0, Var.spielfeld[i], 0, Var.m);
            }
        }
        return bool;
    }

    public static boolean rotate() {
        boolean bool = true; //wird auf false gesetzt, falls move nicht erfolgreich

        int a = 0;
        int b = 0;

        Box[][] tempspielfeld = new Box[Var.n][Var.m]; //zwischenspielfeld

        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() != Var.currentid) {
                    tempspielfeld[i][j] = Var.spielfeld[i][j];
                }
                else {
                    if(Var.spielfeld[i][j].getRotationpoint()) {
                        a = i;
                        b = j;
                    }
                    tempspielfeld[i][j] = new Box(new Color(0,0,0), 0, false);
                }
            }
        }

        loop:
        for(int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    if(a+b-j < 0 || i-a+b < 0 || a+b-j >= Var.n || i-a+b >= Var.m) { //wird geschaut ob es irgendwo an der wand ist
                        System.out.println("Kann nicht bewegt werden!");
                        bool = false;
                        break loop;
                    }
                    else {
                        System.out.println(i + "|" + j + " wird gedreht");
                        tempspielfeld[a+b-j][i-a+b] = Var.spielfeld[i][j];
                    }
                }
            }
        }
        if(bool) {
            for (int i = 0; i < Var.n; i++) {
                if (Var.m >= 0) System.arraycopy(tempspielfeld[i], 0, Var.spielfeld[i], 0, Var.m);
            }
        }
        return bool;
    }
}
