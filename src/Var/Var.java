package Var;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Effects.ImageHelper;
import Game.Box;

public class Var {
    public static int currentid = 1; //ID des aktiven/fallenden Blöcken
    public static String gameState = "startup";
    public static int fps = 60;
    public static int m = 15; //Höhe
    public static int n = 10; //Breite
    public static int tilesX = n; //Anzahl der Felder in x Richtung
    public static int tilesY = m; //Anzahl der Felder in y Richtung
    public static int tilePadding = 5; //Felderabstand
    public static int tileStartX = 438, tileStartY = 622; //Startposition Spielfeld
    public static int tileSize = 36; //Kantenlänge der Felder
    public static Box spielfeld[][] = new Box[n][m]; //Spielfeld mit Box-Objekten
    public static int width = 1280, height = 720;
    public static Color backgroundColor = new Color(24, 21, 33);
    public static boolean stopSignal = false;
    public static int linecounter = 0;
    public static BufferedImage imgBlockI, imgBlockJ, imgBlockL, imgBlockO, imgBlockS, imgBlockT, imgBlockZ, imgBackgroundSingleplayer;

    public Var() {
        //Open Images
        try {
            //Tetrominos
            imgBlockI = ImageHelper.loadImageAsStream("Tetrominos/I.png");
            imgBlockJ = ImageHelper.loadImageAsStream("Tetrominos/J.png");
            imgBlockL = ImageHelper.loadImageAsStream("Tetrominos/L.png");
            imgBlockO = ImageHelper.loadImageAsStream("Tetrominos/O.png");
            imgBlockS = ImageHelper.loadImageAsStream("Tetrominos/S.png");
            imgBlockT = ImageHelper.loadImageAsStream("Tetrominos/T.png");
            imgBlockZ = ImageHelper.loadImageAsStream("Tetrominos/Z.png");

            //Backgrounds
            imgBackgroundSingleplayer = ImageHelper.loadImageAsStream("Backgrounds/Singleplayer.png");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim laden der Bilder!");
        }
    }
}
