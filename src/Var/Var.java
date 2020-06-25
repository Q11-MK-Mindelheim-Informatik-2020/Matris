package Var;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Game.Box;
import Main.Main;
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
    public static int startButtonWidth = 300, startButtonY = 400, getStartButtonImageWidth = getImgDimensions("Buttons/btn_startgame.png", "x"), getStartButtonImageHeight = getImgDimensions("Buttons/btn_startgame.png", "y");
    public static Box[][] spielfeld = new Box[n][m]; //Spielfeld mit Box-Objekten
    public static int width = 1280, height = 720;
    public static Color backgroundColor = new Color(24, 21, 33);
    public static boolean stopSignal = false;
    public static int linecounter = 0;
    public static BufferedImage imgBackgroundPausescreen, imgBackgroundItemBox, imgButtonStartGame, imgButtonStartGameHover, imgButtonStartGamePressed, imgBackgroundHomescreen, imgBlockI, imgBlockJ, imgBlockL, imgBlockO, imgBlockS, imgBlockT, imgBlockZ, imgBackgroundSingleplayer, imgBackgroundTileCornerLeft, imgBackgroundTileCornerUp, imgBackgroundTileCornerRight, imgBackgroundTileCornerDown, imgBackgroundTileInside, imgBackgroundTileSide, imgBackgroundTileSideLeft, imgBackgroundTileSideUp, imgBackgroundTileSideRight, imgBackgroundTileSideDown, imgWholeBrickI, imgWholeBrickJ, imgWholeBrickL, imgWholeBrickO, imgWholeBrickS, imgWholeBrickT, imgWholeBrickZ;
    public static List<Character> bag = new ArrayList<>();
    public static char storedTetromino = 'x';
    public static char currentTetrominoshape;
    public static boolean stored = false;
    public static Timer timer = new Timer();
    public static BufferedImage[] imgWholeBricks = new BufferedImage[91];
    public static HashMap<String, BufferedImage> images = new HashMap<>();

    public Var() {
        //Open Images
        try {
            CodeSource src = Var.class.getProtectionDomain().getCodeSource();

            if(src != null && new File(src.getLocation().getPath()).isFile()) {
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                ZipEntry ze;
                System.out.println(zip.getNextEntry());

                while((ze = zip.getNextEntry() ) != null ) {
                    String entryName = ze.getName();
                    if(entryName.startsWith("Pictures") &&  entryName.endsWith(".png") ) {
                        int index = entryName.lastIndexOf("/");
                        String filename = entryName.substring(index+1, entryName.length()-4);
                        images.put(filename, ImageIO.read(Var.class.getResourceAsStream("/" + entryName)));
                        System.out.println(filename);
                    }
                }
            }
            else {
                URL url = Launcher.class.getResource("/Pictures");
                if (url != null) {
                    try {
                        final File subfolders = new File(url.toURI());
                        for (File subfolder : Objects.requireNonNull(subfolders.listFiles())) {
                            for (File image : Objects.requireNonNull(subfolder.listFiles())) {
                                String imagename = image.getName();
                                images.put(imagename.substring(0,imagename.length()-4), ImageIO.read(image));
                                System.out.println(imagename.substring(0,imagename.length()-4));
                            }
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }

            //Tetrominos
            imgBlockI = loadImageAsStream("Tetrominos/I.png");
            imgBlockJ = loadImageAsStream("Tetrominos/J.png");
            imgBlockL = loadImageAsStream("Tetrominos/L.png");
            imgBlockO = loadImageAsStream("Tetrominos/O.png");
            imgBlockS = loadImageAsStream("Tetrominos/S.png");
            imgBlockT = loadImageAsStream("Tetrominos/T.png");
            imgBlockZ = loadImageAsStream("Tetrominos/Z.png");

            //Tetromino Bricks
            imgWholeBricks['I'] = loadImageAsStream("Tetrominos/I_Brick.png");
            imgWholeBricks['J'] = loadImageAsStream("Tetrominos/J_Brick.png");
            imgWholeBricks['L'] = loadImageAsStream("Tetrominos/L_Brick.png");
            imgWholeBricks['O'] = loadImageAsStream("Tetrominos/O_Brick.png");
            imgWholeBricks['S'] = loadImageAsStream("Tetrominos/S_Brick.png");
            imgWholeBricks['T'] = loadImageAsStream("Tetrominos/T_Brick.png");
            imgWholeBricks['Z'] = loadImageAsStream("Tetrominos/Z_Brick.png");

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
            imgBackgroundItemBox = loadImageAsStream("Backgrounds/item_box.png");
            imgBackgroundPausescreen = loadImageAsStream("Backgrounds/Pausescreen.png");

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
            buffImg = ImageIO.read(Var.class.getResourceAsStream("/Pictures/" + path));
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
