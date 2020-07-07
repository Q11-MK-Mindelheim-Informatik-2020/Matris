package Gui;

import Game.GameStateHandler;
import Game.Mechanics;
import Var.Var;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawHelper {

    //predefined buttons:
    public static DrawHelper startButton = new DrawHelper("btn_startgame", "btn_startgame_pressed","btn_startgame_hover");
    public static DrawHelper restartButton = new DrawHelper("btn_restartgame", "btn_restartgame_pressed","btn_restartgame_hover");

    //public object specific variables
    public int elementWidth, elementHeight, elementX, elementY;

    //private object specific variables
    private String imagePath, imagePathClicked, imagePathHover;
    private boolean tempButtonPress;
    private double imageRatio;

    //initialize variables in constructor
    DrawHelper(String imagePath, String imagePathClicked, String imagePathHover) {
        this.imagePath = imagePath;
        this.imagePathClicked = imagePathClicked;
        this.imagePathHover = imagePathHover;
        this.imageRatio = Var.images.get(imagePath).getHeight() * 1.0 / Var.images.get(imagePath).getWidth();
    }

    //function to create buttons
    public void drawButton(String action, String actionParam, int width, int centerX, int centerY, Graphics g) {
        BufferedImage image = null;
        int height = (int)(imageRatio*width);
        int x = centerX-width/2;
        int y = centerY-height/2;
        if (isInsideBox(Var.mouseX, Var.mouseY, x, y, width, height) && Var.mouseClicked) { //Check if mouse is pressed while hovering over the
            tempButtonPress = true;
            image = Var.images.get(imagePathClicked);
        } else if(isInsideBox(Var.mouseX, Var.mouseY, x, y, width, height)) { //Check if mouse is hovering over the button
            if(tempButtonPress) {
                //executed if mousebutton is released again
                switch (action){ //determines the action of the buttonclick specified by the function parameters
                    case "gamestate": //changes the gamestate
                        GameStateHandler.changeGameState(actionParam);
                        break;
                    case "restart": //restarts the game
                        Mechanics.restartGame();
                        break;
                }
                tempButtonPress = false;
            }
            image = Var.images.get(imagePathHover);
        } else {
            image = Var.images.get(imagePath);
        }
        g.drawImage(image, x, y, width, height,null); //draw button
    }

    //function to draw text
    public static void drawText(String string, int x, int y, Color c, int fontSize, String fontName, Graphics g) {

        //set font size and family
        Font f = new Font(fontName,Font.PLAIN, fontSize);
        g.setFont(f);

        //set font color
        g.setColor(c);

        g.drawString(string, x, y);
    }

    //function to draw background tiles
    public static void drawBackgroundTiles(int tilesX, int tilesY, int padding, Graphics g) {
        int tileSize = ((Var.height - 2*padding) / tilesY);
        BufferedImage img = null;
        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {
                int bgBoardTilePositionX = Var.width / 2 - (tilesX / 2) * tileSize + x * tileSize;
                int bgBoardTilePositionY = Var.height - padding - (y+1)*tileSize;
                if(x==0){
                    if(y==0) {
                        img = Var.images.get("bg_bottom_left_corner");
                    } else if(y==tilesY-1) {
                        img = Var.images.get("bg_upper_left_corner");
                    } else {
                        img = Var.images.get("bg_left_side");
                    }
                } else if(x==tilesX-1) {
                    if(y==0) {
                        img = Var.images.get("bg_bottom_right_corner");
                    } else if(y==tilesY-1) {
                        img = Var.images.get("bg_upper_right_corner");
                    } else {
                        img = Var.images.get("bg_right_side");
                    }
                } else if(y==0) {
                    img = Var.images.get("bg_bottom_side");
                } else if(y==tilesY-1) {
                    img = Var.images.get("bg_upper_side");
                } else {
                    img = Var.images.get("bg_inside");
                }
                g.drawImage(img, bgBoardTilePositionX, bgBoardTilePositionY, tileSize, tileSize, null);
            }
        }
    }

    //function to draw background image
    public static void drawBackgroundImage(String imageName, Graphics g) {
        g.drawImage(Var.images.get(imageName),0, 0, Var.width, Var.height, null);
    }

    //function to draw item boxes
    public static void drawItemBoxes(int boxSize, int boxPadding, int bgTilesX, int bgTilesY, double scalingFactor, int boxCount, Graphics g) {
        int tempBoxSize = boxSize;
        int bgTileSize = ((Var.height - 2*boxPadding) / bgTilesY);
        int cornerPointX = Var.width/2 + (bgTileSize*bgTilesX)/2 + boxPadding;
        int cornerPointY = Var.height - boxPadding - (bgTilesY)*bgTileSize;
        g.drawImage(Var.images.get("item_box"), Var.width - cornerPointX - tempBoxSize, cornerPointY, tempBoxSize, tempBoxSize, null);
        g.drawImage(Var.images.get(Var.storedTetromino + "_Brick"), Var.width - cornerPointX - tempBoxSize, cornerPointY, tempBoxSize, tempBoxSize, null);
        for (int i = 0; i < boxCount; i++) {
            g.drawImage(Var.images.get("item_box"), cornerPointX, cornerPointY, tempBoxSize, tempBoxSize, null);
            g.drawImage(Var.images.get(Var.bag.get(i) + "_Brick"), cornerPointX, cornerPointY, tempBoxSize, tempBoxSize, null);
            cornerPointY += tempBoxSize + boxPadding;
            if(i==0) { boxSize *= scalingFactor; }
        }
    }

    //function to draw Tetrominos
    public static void drawTetrominos(int tilePadding, int bgTilesX, int bgTilesY, int padding, Graphics g) {
        int bgTileSize = ((Var.height - 2*Var.padding) / Var.tilesY);
        int cornerX = Var.width/2 - (bgTileSize*bgTilesX)/2 + tilePadding;
        int tileSize = (bgTileSize*bgTilesX-tilePadding)/bgTilesX;
//        System.out.println(((tilePadding*bgTilesX)-1)/bgTilesX);
        int cornerY = Var.height-padding;
        for (int x = 0; x < bgTilesX; x++) {
            for (int y = 0; y < bgTilesY; y++) {
                if (Var.spielfeld[x][y].getTileTexture() != null) {
                    g.drawImage(Var.spielfeld[x][y].getTileTexture(), cornerX + tilePadding + x * (tileSize), cornerY - tilePadding - (y+1) * (tileSize), tileSize - tilePadding, tileSize - tilePadding, null);
                }
            }
        }
    }

    //function to check coordinate collisions
    private boolean isInsideBox(int testX, int testY, int x, int y, int width, int height) {
        return testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height);
    }
}
