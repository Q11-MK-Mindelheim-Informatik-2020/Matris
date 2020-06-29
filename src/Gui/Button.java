package Gui;

import Game.GameStateHandler;
import Game.Mechanics;
import Var.Var;

import java.awt.image.BufferedImage;

public class Button {

    public static Button startButton = new Button("gamestate", "singleplayer", "btn_startgame", "btn_startgame_pressed","btn_startgame_hover",400, Var.width/2, Var.height/2);
    public static Button restartButton = new Button("restart", "", "btn_restartgame", "btn_restartgame_pressed","btn_restartgame_hover",400, Var.width/2, Var.height-Var.height/4);

    private String action;
    private String actionParam;
    private String imagePathUnclicked;
    private String imagePathClicked;
    private String imagePathHover;
    public int buttonWidth;
    public int buttonHeight;
    public int buttonX;
    public int buttonY;
    private boolean tempButtonPress;

    Button(String action, String actionParam, String imagePathUnclicked, String imagePathClicked, String imagePathHover, int buttonWidth, int buttonCenterpointX, int buttonCenterpointY) {
        this.action = action;
        this.actionParam = actionParam;
        this.imagePathUnclicked = imagePathUnclicked;
        this.imagePathClicked = imagePathClicked;
        this.imagePathHover = imagePathHover;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = Var.images.get(imagePathClicked).getHeight() * buttonWidth / Var.images.get(imagePathClicked).getWidth();
        this.buttonX = buttonCenterpointX-buttonWidth/2;
        this.buttonY = buttonCenterpointY-buttonHeight/2;
    }

    public BufferedImage image() {
        if (isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, buttonWidth, buttonHeight) && Var.mouseClicked) {
            tempButtonPress = true;
            return Var.images.get(imagePathClicked);
        } else if(isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            if(tempButtonPress) {
                switch (action){
                    case "gamestate":
                        GameStateHandler.changeGameState(actionParam);
                        break;
                    case "restart":
                        Mechanics.restartGame();
                        break;
                }
                tempButtonPress = false;
            }
            return Var.images.get(imagePathHover);
        } else {
            return Var.images.get(imagePathUnclicked);
        }
    }

    private boolean isInsideBox(int testX, int testY, int x, int y, int width, int height) {
        return testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height);
    }
}
