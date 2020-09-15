package Var;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.CodeSource;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Game.Box;
import sun.misc.Launcher;

import javax.imageio.ImageIO;
import javafx.scene.media.Media;

public class Var {

    // Optionsvariablen
    public static int fps = 30;
    public static int m = 24; //Größe des Spielfeld-arrays: [n][m]
    public static int n = 10;
    public static boolean ghostmode = false;
    public static int volume = 50;
    public static double ARR = 2, DAS = 12;

    //Gui-Variabeln
    public static int width = 1280, height = 720;
    public static int tilesX = n; //Anzahl der Felder in x Richtung
    public static int tilesY = m-4; //Anzahl der (sichtbaren) Felder in y Richtung
    public static int tilePadding = 3; //Felderabstand
    public static int itemBoxSize = 7, itemBoxCount = 5;
    public static double itemScalingFactor = 0.8;
    public static int padding = 5;
    public static boolean mouseClicked = false;
    public static int mouseX = 0, mouseY = 0;
    public static boolean tempCheckboxClick = false, tempSliderTouched = false, mouseDragged;
    public static Color backgroundColor = new Color(24, 21, 33), fontColor = new Color(22,96,170);
    public static String gameState = "startup";
    public static int currentBackgroundID = (int) (Math.random()*20);


    //Spiel-Variabeln
    public static int score, level, linecounter;
    public static Box[][] spielfeld; //Spielfeld mit Box-Objekten
    public static int currentid = 1; //ID des aktiven/fallenden Blöcken

    public static char storedTetromino = 'x';
    public static char currentTetrominoshape;
    public static boolean stored = false;

    public static HashMap<Character, int[][]> Tetrominos = new HashMap<>();

    public static List<Character> bag = new ArrayList<>();

    public static Timer timer = new Timer();


    //Medien-Objekte
    public static HashMap<String, BufferedImage> images = new HashMap<>();
    public static HashMap<String, Media> sounds = new HashMap<>();



    public Var() {
        //Open Images
        try {
            CodeSource src = Var.class.getProtectionDomain().getCodeSource();
            String protocol = this.getClass().getResource("").getProtocol();

            if(src != null && Objects.equals(protocol, "jar")) { //Code für jar
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                ZipEntry ze;
                while((ze = zip.getNextEntry() ) != null ) {
                    String entryName = ze.getName();
                    if(entryName.startsWith("Pictures") &&  (entryName.endsWith(".png") || entryName.endsWith(".jpeg"))) {
                        int index = entryName.lastIndexOf("/");
                        String filename = entryName.substring(index+1, entryName.length()-4 - (entryName.endsWith(".jpeg") ? 1 : 0));
                        images.put(filename, ImageIO.read(Var.class.getResourceAsStream("/" + entryName)));
                        //System.out.println(filename);
                    }
                    if (entryName.startsWith("Sounds") && entryName.endsWith(".mp3")) {
                        String filename = entryName.substring(entryName.lastIndexOf("/")+1);
                        //System.out.println(filename);
                        try {
                            sounds.put(filename, new Media(Var.class.getResource("/" + entryName).toURI().toString()));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else { //Code für IDE
                URL url = Launcher.class.getResource("/Pictures");
                if (url != null) {
                    try {
                        final File subfolders = new File(url.toURI());
                        for (File subfolder : Objects.requireNonNull(subfolders.listFiles())) {
                            for (File image : Objects.requireNonNull(subfolder.listFiles())) {
                                String imagename = image.getName();
                                images.put(imagename.substring(0,imagename.length()-4 - (imagename.endsWith(".jpeg") ? 1 : 0)), ImageIO.read(image));
                                //System.out.println(imagename.substring(0,imagename.length()-4));
                            }
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                URL url2 = Launcher.class.getResource("/Sounds");
                if (url2 != null) {
                    try {
                        final File subfolders = new File(url2.toURI());
                        for (File subfolder : Objects.requireNonNull(subfolders.listFiles())) {
                            for (File sound : Objects.requireNonNull(subfolder.listFiles())) {
                                String soundname = sound.getName();
                                sounds.put(soundname, new Media(sound.toURI().toString()));
                            }
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim laden der Bilder/Sounds!");
        }

        updateTetrominos();
    }

    public static void updateTetrominos() {
        //Tetrominos
        int k = (int) Math.ceil(Var.n/2.0);
        Tetrominos.put('I', new int[][]{
                {k-1, Var.tilesY-1},
                {k-2, Var.tilesY-1},
                {k, Var.tilesY-1},
                {k+1, Var.tilesY-1}});
        Tetrominos.put('O', new int[][]{
                {k-1, Var.tilesY-1},
                {k, Var.tilesY-1},
                {k-1, Var.tilesY-2},
                {k, Var.tilesY-2}});
        Tetrominos.put('T', new int[][]{
                {k-1, Var.tilesY-1},
                {k-2, Var.tilesY-1},
                {k, Var.tilesY-1},
                {k-1, Var.tilesY-2}});
        Tetrominos.put('S', new int[][]{
                {k-1, Var.tilesY-1},
                {k, Var.tilesY-1},
                {k-1, Var.tilesY-2},
                {k-2, Var.tilesY-2}});
        Tetrominos.put('Z', new int[][]{
                {k-1, Var.tilesY-1},
                {k-2, Var.tilesY-1},
                {k-1, Var.tilesY-2},
                {k, Var.tilesY-2}});
        Tetrominos.put('J', new int[][]{
                {k-1, Var.tilesY-1},
                {k-2, Var.tilesY-1},
                {k, Var.tilesY-1},
                {k, Var.tilesY-2}});
        Tetrominos.put('L', new int[][]{
                {k-1, Var.tilesY-1},
                {k-2, Var.tilesY-1},
                {k, Var.tilesY-1},
                {k-2, Var.tilesY-2}});
    }
}
