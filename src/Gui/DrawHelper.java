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
    public static DrawHelper enterButton = new DrawHelper("btn_enter", "btn_enter_click","btn_enter_hover");
    public static DrawHelper optButton = new DrawHelper("btn_opt", "btn_opt_click","btn_opt_hover");
    public static DrawHelper helpButton = new DrawHelper("btn_help", "btn_help_click","btn_help_hover");
    public static DrawHelper resButton = new DrawHelper("btn_res", "btn_res_click","btn_res_hover");
    public static DrawHelper menuButton = new DrawHelper("btn_menu", "btn_menu_click","btn_menu_hover");


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
                Effects.Sounds.playSound("another_menu_switch.mp3", 1.0, false);
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

    //function to draw checkbox
    public static void drawCheckbox(String labelText, Color labelColor,  boolean actionVariable, int cornerX, int boxCornerX, int cornerY, int size, String fontName, Runnable run, Graphics g) {
        BufferedImage image = null;
        drawText(labelText, cornerX, cornerY, labelColor, size, fontName, g);
        if (isInsideBox(Var.mouseX, Var.mouseY, boxCornerX+size/2, cornerY-size, size, size) && Var.mouseClicked) { //Check if mouse is pressed while hovering over the
            Var.tempCheckboxClick = true;
            if(actionVariable) { image = Var.images.get("checkbox_click_checked"); } else { image = Var.images.get("checkbox_click_unchecked"); }
        } else if(isInsideBox(Var.mouseX, Var.mouseY, boxCornerX+size/2, cornerY-size, size, size)) { //Check if mouse is hovering over the button
            if(Var.tempCheckboxClick) {
                run.run();
                Var.tempCheckboxClick = false;
            }
            if(actionVariable) { image = Var.images.get("checkbox_hover_checked"); } else { image = Var.images.get("checkbox_hover_unchecked"); }
        } else {
            if(actionVariable) { image = Var.images.get("checkbox_idle_checked"); } else { image = Var.images.get("checkbox_idle_unchecked"); }
        }
        g.drawImage(image, boxCornerX+size/2, cornerY-size, size, size, null);
    }

    public static double drawSlider(String labelText, Color labelColor, double actionVariable, int cornerX, int sliderCornerX, int cornerY, int height, int width, double step, double start, double end, String fontName, Graphics g) {
        BufferedImage image = null;
        double returnValue = actionVariable;
        int buttonX = (int)(sliderCornerX+Math.floor((actionVariable-start)/step)*step*(width/(end-start)));
        int buttonY = cornerY;
        drawText(labelText, cornerX, cornerY+(int)(height*0.8), labelColor, height, fontName, g);
        g.drawImage(Var.images.get("slider_bar"), sliderCornerX + height/2, cornerY + height/3, width, height/3, null);
        if ((isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, height, height) && Var.mouseClicked) || (Var.tempSliderTouched && Var.mouseDragged && isInsideBox(Var.mouseX, Var.mouseY, sliderCornerX-height, cornerY-height/2, width+height*2, height*2))) { //Check if mouse is pressed while hovering over the
            Var.tempSliderTouched = true;
            double boxMousePercent = (( Var.mouseX-sliderCornerX )/(double)width);
            if(boxMousePercent<=0) {
                buttonX = sliderCornerX;
                returnValue = start;
            } else if(boxMousePercent>=1) {
                buttonX = sliderCornerX+width;
                returnValue = end;
            } else {
                double value = (Math.floor((((Var.mouseX - sliderCornerX) / (double) width) * (end - start)) / step) * step)+start;
                buttonX = (int) (sliderCornerX + (value-start) * (width / (end - start)));
                returnValue = value;
            }
            image = Var.images.get("slider_button_click");
        } else if(isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, height, height)) { //Check if mouse is hovering over the button
            if(Var.tempSliderTouched) {
                Var.tempSliderTouched = false;
            }
            image = Var.images.get("slider_button_hover");
        } else {
            image = Var.images.get("slider_button_idle");
        }
        g.drawImage(image, buttonX, buttonY, height, height, null);
        return returnValue;
    }

    //function to draw background tiles
    public static void drawBackgroundTiles(int tilesX, int tilesY, int padding, Graphics g) {
        int tileSize = ((Var.height - 2*padding) / tilesY);
        BufferedImage img = null;
        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {
                int bgBoardTilePositionX = (int)(Var.width / 2.0 - (tilesX / 2.0) * tileSize + x * tileSize);
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
        BufferedImage image = Var.images.get(imageName);
        double ratio = (double)image.getWidth()/image.getHeight();
        int x, y, width, height;
        if((double)Var.width/image.getWidth() <= (double)Var.height/image.getHeight()) {
            height = Var.height;
            y = 0;
            width = (int)(height*ratio);
            x = Var.width/2-width/2;
        } else {
            width = Var.width;
            x = 0;
            height = (int)(width/ratio);
            y = Var.height/2-height/2;
        }
        g.drawImage(image,x, y, width, height, null);
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
        int tileSize = (int)((bgTileSize*bgTilesX-tilePadding)/(double)bgTilesX);
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
    private static boolean isInsideBox(int testX, int testY, int x, int y, int width, int height) {
        return testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height);
    }

}
