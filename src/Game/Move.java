package Game;

import Var.Var;

import java.awt.*;
import java.util.ArrayList;

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
    public static void downdown() {
        int y = Var.m-1;
        while (!move(0,-y)) {
            y--;
        }
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
                    tempspielfeld[i][j] = new Box(null, 0);
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
                    else {
                        //System.out.println(i + "|" + j + " wird verschoben");
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
                    tempspielfeld[i][j] = new Box(null, 0, false);
                }
            }
        }

        loop:
        for(int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    if(a+b-j < 0 || i-a+b < 0 || a+b-j >= Var.n || i-a+b >= Var.m || (Var.spielfeld[a+b-j][i-a+b].getId() != 0 && Var.spielfeld[a+b-j][i-a+b].getId() != Var.currentid)) { //wird geschaut ob es irgendwo an der wand ist oder beim drehen ein stein im weg ist
                        //System.out.println("Kann nicht bewegt werden!");
                        bool = false;
                        break loop;
                    }
                    else {
                        //System.out.println(i + "|" + j + " wird gedreht");
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

    public static void removeLines(ArrayList<Integer> lines) {
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

    }
}