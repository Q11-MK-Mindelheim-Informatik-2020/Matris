package Var;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import Game.Box;
import Main.Main;

import javax.imageio.ImageIO;

public class Var {
    public static int currentid = 1; //ID des aktiven/fallenden Blöcken
    public static String gameState = "startup";
    public static boolean mouseClicked = false;
    public static int mouseX = 0, mouseY = 0;
    public static int fps = 30;
    public static int m = 24; //Größe des Spielfeld-arrays: [n][m]
    public static int n = 10;
    public static int tilesX = n; //Anzahl der Felder in x Richtung
    public static int tilesY = m-4; //Anzahl der (sichtbaren) Felder in y Richtung
    public static int tilePadding = 3; //Felderabstand
    public static int tileStartX = 472, tileStartY = 646; //Startposition Spielfeld
    public static int tileSize = 31; //Kantenlänge der Felder
    public static double bgBoardPaddingPercent = 1;
    public static boolean startButtonPressed = false;
    public static int startButtonWidth = 300, startButtonY = 400, getStartButtonImageWidth = getImgDimensions("/Pictures/Buttons/btn_startgame.png", "x"), getStartButtonImageHeight = getImgDimensions("/Pictures/Buttons/btn_startgame.png", "y");
    public static Box[][] spielfeld = new Box[n][m]; //Spielfeld mit Box-Objekten
    public static int width = 1280, height = 720;
    public static Color backgroundColor = new Color(24, 21, 33);
    public static boolean stopSignal = false;
    public static int linecounter = 0;
    public static BufferedImage imgButtonStartGame, imgButtonStartGameHover, imgButtonStartGamePressed, imgBackgroundHomescreen, imgBlockI, imgBlockJ, imgBlockL, imgBlockO, imgBlockS, imgBlockT, imgBlockZ, imgBackgroundSingleplayer, imgBackgroundTileCornerLeft, imgBackgroundTileCornerUp, imgBackgroundTileCornerRight, imgBackgroundTileCornerDown, imgBackgroundTileInside, imgBackgroundTileSide, imgBackgroundTileSideLeft, imgBackgroundTileSideUp, imgBackgroundTileSideRight, imgBackgroundTileSideDown;
    public static List<Character> bag = new ArrayList<>();
    public static char storedTetromino = 'x';
    public static char currentTetrominoshape;
    public static boolean stored = false;
    public static Timer timer = new Timer();

    public Var() {
        //Open Images
        try {
            //Tetrominos
            imgBlockI = loadImageAsStream("Tetrominos/I.png");
            imgBlockJ = loadImageAsStream("Tetrominos/J.png");
            imgBlockL = loadImageAsStream("Tetrominos/L.png");
            imgBlockO = loadImageAsStream("Tetrominos/O.png");
            imgBlockS = loadImageAsStream("Tetrominos/S.png");
            imgBlockT = loadImageAsStream("Tetrominos/T.png");
            imgBlockZ = loadImageAsStream("Tetrominos/Z.png");

            //Backgrounds
            imgBackgroundSingleplayer = loadImageAsStream("Backgrounds/Singleplayer.png");
            imgBackgroundTileCornerLeft = loadImageAsStream("Backgrounds/bg_corner_1.png");
            imgBackgroundTileCornerUp = loadImageAsStream("Backgrounds/bg_corner_2.png");
            imgBackgroundTileCornerRight = loadImageAsStream("Backgrounds/bg_corner_3.png");
            imgBackgroundTileCornerDown = loadImageAsStream("Backgrounds/bg_corner_4.png");
            imgBackgroundTileInside = loadImageAsStream("Backgrounds/bg_inside.png");
            imgBackgroundTileSideLeft = loadImageAsStream("Backgrounds/bg_side_1.png");
            imgBackgroundTileSideUp = loadImageAsStream("Backgrounds/bg_side_2.png");
            imgBackgroundTileSideRight = loadImageAsStream("Backgrounds/bg_side_3.png");
            imgBackgroundTileSideDown = loadImageAsStream("Backgrounds/bg_side_4.png");
            imgBackgroundHomescreen = loadImageAsStream("Backgrounds/Startscreen.png");

            //Buttons
            imgButtonStartGame = loadImageAsStream(("Buttons/btn_startgame.png"));
            imgButtonStartGamePressed = loadImageAsStream(("Buttons/btn_startgame_pressed.png"));
            imgButtonStartGameHover = loadImageAsStream(("Buttons/btn_startgame_hover.png"));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim laden der Bilder!");
        }
    }

    private static BufferedImage loadImageAsStream(String fileName) throws IOException {
        return ImageIO.read(Var.class.getResourceAsStream("/Pictures/" + fileName));
    }

    public static int getImgDimensions(String path, String axis){
        BufferedImage buffImg = null;
        try {
            buffImg = ImageIO.read(Var.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int dimension = 0;
        switch (axis) {
            case "x":
                assert buffImg != null;
                dimension = buffImg.getWidth();
                break;
            case "y":
                assert buffImg != null;
                dimension = buffImg.getHeight();
                break;
        }
        return dimension;
    }


}
