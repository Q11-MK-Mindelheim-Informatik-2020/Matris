package Game;

import Effects.Sounds;
import Var.Var;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Move {

    private static boolean deactived = false;

    /**
     * Eine Einheit nach rechts verschieben
     * @return Boolean ob Move erfolgreich
     */
    public static boolean right() {
        return move(1,0);
    }

    /**
     * Eine Einheit nach links verschieben
     * @return Boolean ob Move erfolgreich
     */
    public static boolean left() {
        return move(-1,0);
    }

    /**
     * Eine Einheit nach unten verschieben
     * @return Boolean ob Move erfolgreich
     */
    public static boolean down() {
        return move(0,-1);
    }

    /**
     * Eine Einheit nach oben verschieben
     * @return Boolean ob Move erfolgreich
     */
    static boolean up() {
        return move(0,1);
    }

    /**
     * Stein mit Currentid wird möglichst weit nach unten verschoben
     */
    public static void downdown() {
        if (!deactived) {
            int y; //Höhe bei dem es gerade nicht mehr funktioniert zu verschieben
            if (Var.ghostmode) { //Modus bei dem man durch Steine "durchbuggen" kann
                y = Var.m-1; //mit der größten Höhe angefangen
                //Solange es nicht möglich ist, y Einheiten nach unten zu verschieben, y um eins erniedrigen
                while (!move(0,-y)) { //wenn es möglich ist, wird der stein verschoben
                    y--;
                }
            }
            else {
                y = 0; //mit der kleinsten Höhe angefangen
                //Solange es möglich ist, y Einheiten nach unten zu verschieben, y um eins erhöhen
                while (move(0,-y,false)) {
                    y++;
                }
                y--;
                //Stein um y Einheiten nach unten verschieben, denn dies ist gerade noch möglich
                move(0,-y);
            }
            Var.score = Var.score + 2*y;

            //momentaner Timer stoppen und neu ausführen, da momentaner spielzug zwingend zuende ist
            Mechanics.resetTimer();
            Var.timer.schedule(new Mechanics(), 0);
        }
    }

    /**
     * Stein mit der Currentid wird
     * @param x Einheiten nach rechts verschoben
     * @param y Einheiten nach links verschoben
     * @param handle wenn true, dann wird der Stein auch verschoben, wenn falls wird nur überprüft ob es möglich ist
     * @return boolean Wert, ob der Move erfolgreich ist/wäre
     */
    private static boolean move(int x, int y, boolean handle) {
        if (deactived) {
            return false;
        }
        else {
            boolean bool = true; //wird auf false gesetzt, falls das Verschieben nicht erfolgreich war
            Box[][] tempspielfeld = new Box[Var.n][Var.m]; //Zwischenspielfeld

            //Alle Steine, bis auf die mit der Currentid, werden auf das tempspielfeld kopiert (wenn handle==true)
            //Außerdem werden die Vorschau/Marker Texturen nicht mit übernommen
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

            //Prüfen ob Spielzug möglich ist und ausführen wenn handle==true
            loop:
            for(int i = 0; i < Var.n; i++) {
                for (int j = 0; j < Var.m; j++) {
                    if(Var.spielfeld[i][j].getId() == Var.currentid) {
                        if(i+x < 0 || j+y < 0 || i+x >= Var.n || j+y >= Var.m || //prüfen, ob stein "in der wand" wäre nach dem verschieben
                                (Var.spielfeld[i+x][j+y].getId() != 0 && Var.spielfeld[i+x][j+y].getId() != Var.currentid)) { //prüfen ob Stein ungleich der Currentid im weg ist
                            //Spielzug wäre nicht erfolgreich
                            bool = false;
                            break loop; //Schleife beenden
                        }
                        else if(handle) {
                            tempspielfeld[i+x][j+y] = Var.spielfeld[i][j]; //verschieben wenn handle==true im Zwischenspielfeld
                        }
                    }
                }
            }
            //Wenn spielzug erfolgreich ist (bool==true) und handle==true wird das tempspielfeld zum orginalen Spielfeld kopiert
            if(bool && handle) {
                for (int i = 0; i < Var.n; i++) {
                    if (Var.m >= 0) System.arraycopy(tempspielfeld[i], 0, Var.spielfeld[i], 0, Var.m);
                }
                showPreview(); //Anzeigen der Vorschau
            }
            return bool;
        }
    }

    /**
     * Standardwert für handle ist true
     * siehe move Funktion
     */
    private static boolean move(int x, int y) {
        return move(x,y,true);
    }

    /**
     * Stein mit Currentid wird versucht zu drehen (im Uhrzeigersinn)
     * @return boolean Wert, ob die Drehung erfolgreich war
     */
    public static boolean rotate() {
        if (deactived) {
            return false;
        }
        else {
            boolean bool = true; //wird auf false gesetzt, falls die Drehung nicht erfolgreich war

            //Variabeln zum Speichern des Rotationspunkt
            int a = 0;
            int b = 0;

            Box[][] tempspielfeld = new Box[Var.n][Var.m]; //Zwischenspielfeld

            //Alle Steine, bis auf die mit der Currentid, werden auf das tempspielfeld kopiert und Rotationspunkt einspeichern
            //Außerdem werden die Vorschau/Marker Texturen nicht mit übernommen
            for (int i = 0; i < Var.n; i++) {
                for (int j = 0; j < Var.m; j++) {
                    if(Var.spielfeld[i][j].getId() != Var.currentid && Var.spielfeld[i][j].getId() != 0) {
                        tempspielfeld[i][j] = Var.spielfeld[i][j];
                    }
                    else {
                        if(Var.spielfeld[i][j].getRotationpoint()) {
                            //Einspeichern Rotationspunkt
                            a = i;
                            b = j;
                        }
                        tempspielfeld[i][j] = new Box(0);
                    }
                }
            }

            //Prüfen, ob rotieren möglich ist und eventuell rotieren
            loop:
            for(int i = 0; i < Var.n; i++) {
                for (int j = 0; j < Var.m; j++) {
                    if(Var.spielfeld[i][j].getId() == Var.currentid) {
                        if(a-b+j < 0 || a+b-i < 0 || a-b+j >= Var.n || a+b-i >= Var.m || //prüfen, ob stein "in der wand" wäre nach dem rotieren
                                (Var.spielfeld[a-b+j][a+b-i].getId() != 0 && Var.spielfeld[a-b+j][a+b-i].getId() != Var.currentid)) { //prüfen ob Stein ungleich der Currentid im weg ist
                            bool = false;
                            break loop; //Schleife beenden
                        }
                        else {
                            //rotatieren des Steins mit der Currentid um den Rotationspunkt (a,b)
                            //siehe: https://imgur.com/a/7DYPNUF
                            tempspielfeld[a-b+j][a+b-i] = Var.spielfeld[i][j];
                        }
                    }
                }
            }

            //Wenn spielzug erfolgreich ist (bool==true), wird das tempspielfeld zum orginalen Spielfeld kopiert
            if(bool) {
                for (int i = 0; i < Var.n; i++) {
                    if (Var.m >= 0) System.arraycopy(tempspielfeld[i], 0, Var.spielfeld[i], 0, Var.m);
                }
                showPreview(); //Anzeigen der Vorschau
            }
            return bool; //Rückgabe, ob Rotation erfolgreich war
        }
    }

    /**
     * Entfernen von Linien
     * @param lines Höhen von Linien
     */
    static void removeLines(ArrayList<Integer> lines) throws InterruptedException {
        //Alle linien mit der Höhe j (in der Arraylist) werden gelöscht.

        deactived = true;

        if (Var.n % 2 == 1) {
            for (int j : lines) {
                Var.spielfeld[Var.n/2+1][j] = new Box(0);
            }
            Thread.sleep(100);
            for (int i = 1; i < Var.n/2; i++) {
                for (int j: lines) {
                    Var.spielfeld[Var.n/2+1-i][j] = new Box(0);
                    Var.spielfeld[Var.n/2+1+i][j] = new Box(0);
                }
                Thread.sleep(100);
            }
        }
        else {
            for (int i = 0; i < Var.n/2; i++) {
                for (int j : lines) {
                    Var.spielfeld[Var.n/2+i][j] = new Box( 0);
                    Var.spielfeld[Var.n/2-1-i][j] = new Box( 0);
                }
                Thread.sleep(100);
            }
        }

        deactived = false;

        //Linien nach unten bewegen
        for (int j = 1; j < Var.m; j++) {
            int z = 0; //Anzahl der Felder, die die j-te Linie nach unten muss
            for (int k : lines) {
                if (j > k) {
                    //für jede Linie die unter der j-ten Linie entfernt wurde, wird z um eins erhöht
                    z++;
                }
                else {
                    break;
                }
            }
            //Wenn z ungleich null ist, wird die j-te Linie um z Einheiten nach unten verschoben
            if(z !=0) {
                for (int i = 0; i < Var.n; i++) {
                    Var.spielfeld[i][j-z] = Var.spielfeld[i][j];
                    Var.spielfeld[i][j] = new Box(0);
                }
            }
        }
        int l = lines.size(); //Anzahl der linien
        Var.score += (Var.level+1)*(5*l*(l*(l*(11*l - 54) + 91) - 24))/3;
        switch (l) {
            case 1:
                Sounds.playSound("holyshit.mp3", 1.0, false);
                break;
            case 2:
                Sounds.playSound("dominating.mp3", 1.0, false);
                break;
            case 3:
                Sounds.playSound("rampage.mp3", 1.0, false);
                break;
            case 4:
                Sounds.playSound("godlike.mp3", 1.0, false);
                break;
        }
        //Polynom vierten Grades in Horner Form, das die Punkte für l Linien berechnet.
        //Siehe https://tetris.wiki/Scoring#Original_Nintendo_scoring_system
    }

    /**
     * Tetromino mit der Currentid wird entfernt
     */
    private static void removeTetromino() {
        int c = 0; //Zähler: wie viele Blöcke wurden bereits entfernt
        loop:
        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid) {
                    Var.spielfeld[i][j] = new Box(0);
                    c++;
                    if(c == 4) { //wenn 4 Blöcke entfernt wurden
                        //Da Tetrominos aus 4 Blöcken besteht, kann die Schleife beendet werden
                        break loop;
                    }
                }
            }
        }
    }

    /**
     * Anzeige der Vorschau
     */
    static void showPreview() {
        int y; //größtmögliche Höhe zum nach unten verschieben

        if (Var.ghostmode) { //Modus bei dem man durch Steine "durchbuggen" kann
            y = Var.m-1; //mit der größten Höhe angefangen
            while (!move(0,-y, false)) { //solange es nicht möglich ist y Einheiten nach unten zu verschieben, y um eins erniedrigen
                y--;
            }
        }
        else {
            y = 0; //mit der kleinsten Höhe anfangen
            while (move(0,-y,false) && y < Var.m) { //solange es möglich y Einheiten nach unten zu verschieben, y um eins erhöhen
                y++;
            }
            y--; //y um eins erniedrigen, denn das ist gerade die Höhe, bei der es noch funktioniert
        }

        //Vorschau/Marker einspeichern in Spielfeld
        for (int i = 0; i < Var.n; i++) {
            for (int j = 0; j < Var.m; j++) {
                if(Var.spielfeld[i][j].getId() == Var.currentid && Var.spielfeld[i][j-y].getId() == 0) {  //marker nur hinzugügen wenn das feld (i,j-y) leer ist
                    Var.spielfeld[i][j-y] = new Box(Var.images.get("Marker"), 0); //marker hinzufügen
                }
                else if (Var.spielfeld[i][j].getId() == 0 && Var.spielfeld[i][j].getTileTexture() != null) { //entfernen von alten markern
                    Var.spielfeld[i][j] = new Box(null, 0);
                }

            }
        }
    }

    public static boolean store() {
        if (Var.gameState.equals("singleplayer") && !Var.stored) {
            Mechanics.resetTimer();
            char shape = Var.currentTetrominoshape;
            removeTetromino();
            if (Var.storedTetromino == 'x') {
                Tetromino.spawnRandom();
            }
            else {
                Tetromino.spawn(Var.storedTetromino);
            }
            Var.storedTetromino = shape;
            Var.timer.schedule(new Mechanics(), Mechanics.getTime());
            Var.stored = true;
            return true;
        }
        else {
            return false;
        }
    }
}