package Var;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Game.Box;
import sun.misc.Launcher;

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
    public static int itemBoxPadding = 20, itemBoxSize = 90, itemBoxCount = 5;
    public static double secondaryItemBoxScalingFactor = 0.7, itemScalingFactor = 0.8;
    public static int tileStartX = 472, tileStartY = 646; //Startposition Spielfeld
    public static int tileSize = 31; //Kantenlänge der Felder
    public static double bgBoardPaddingPercent = 1;
    public static boolean startButtonPressed = false;
    public static int startButtonWidth = 300, startButtonY = 400;
    public static Box[][] spielfeld = new Box[n][m]; //Spielfeld mit Box-Objekten
    public static int width = 1280, height = 720;
    public static Color backgroundColor = new Color(24, 21, 33);
    public static boolean stopSignal = false;
    public static int linecounter = 0;
    public static List<Character> bag = new ArrayList<>();
    public static char storedTetromino = 'x';
    public static char currentTetrominoshape;
    public static boolean stored = false;
    public static Timer timer = new Timer();
    public static HashMap<String, BufferedImage> images = new HashMap<>();

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
                    if(entryName.startsWith("Pictures") &&  entryName.endsWith(".png") ) {
                        int index = entryName.lastIndexOf("/");
                        String filename = entryName.substring(index+1, entryName.length()-4);
                        images.put(filename, ImageIO.read(Var.class.getResourceAsStream("/" + entryName)));
                        //System.out.println(filename);
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
                                images.put(imagename.substring(0,imagename.length()-4), ImageIO.read(image));
                                //System.out.println(imagename.substring(0,imagename.length()-4));
                            }
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim laden der Bilder!");
        }
    }
}
