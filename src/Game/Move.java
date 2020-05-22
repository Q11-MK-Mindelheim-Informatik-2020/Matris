package Game;

import Var.Var;

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

    private static boolean move(int x, int y) { //Stein mit currentid wird versucht zu verschieben
        boolean bool = true; //wird auf false gesetzt, falls move nicht erfolgreich
        Box[][] tempspielfeld = Var.spielfeld; //zwischenspielfeld
        loop:
        for(int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].id == Var.currentid) {
                    if(i+x < 0 || j+y < 0 || i+x >= Var.n || j+y >= Var.m) { //wird geschaut ob es irgendwo an der wand ist
                        System.out.println("Kann nicht bewegt werden!");
                        bool = false;
                        break loop;
                    }
                    else {
                        System.out.println(i + "|" + j + " wird verschoben");

                        //Fehler, da tempspielfeld irgendwie einfluss auf Var.spielfeld hat
                        //tempspielfeld[i+x][j+y] = Var.spielfeld[i][j];
                        //tempspielfeld[i][j] = new Box(new Color(0,0,0), 0, false);
                    }
                }
            }
        }
        if(bool) {
            Var.spielfeld = tempspielfeld; //falls verschieben geht wird das temporäre spielffeld gleich dem spielffeld gesetzt
        }
        return bool;
    }
}
