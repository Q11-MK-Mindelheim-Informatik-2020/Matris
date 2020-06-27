package Gui;

import Var.Var;

import java.awt.image.BufferedImage;

public class Button {

    public static Button startButton = new Button("btn_startgame","btn_startgame_hover",400, Var.width/2, Var.height/2);

    String imagePathUnclicked;
    String imagePathClicked;
    public int buttonWidth;
    public int buttonHeight;
    public int buttonX;
    public int buttonY;

    Button(String imagePathUnclicked, String imagePathClicked, int buttonWidth, int buttonCenterpointX, int buttonCenterpointY) {
        this.imagePathUnclicked = imagePathUnclicked;
        this.imagePathClicked = imagePathClicked;
        this.buttonWidth = buttonWidth;
        this.buttonHeight = Var.images.get(imagePathClicked).getHeight() * buttonWidth / Var.images.get(imagePathClicked).getWidth();
        this.buttonX = buttonCenterpointX-buttonWidth/2;
        this.buttonY = buttonCenterpointY-buttonHeight/2;
    }

    public BufferedImage image() {
        if (isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
            return Var.images.get(imagePathClicked);
        } else {
            return Var.images.get(imagePathUnclicked);
        }
    }

    private boolean isInsideBox(int testX, int testY, int x, int y, int width, int height) {
        return testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height);
    }
}
