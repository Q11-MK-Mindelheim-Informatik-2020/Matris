package Gui;

import Game.GameStateHandler;
import Var.Var;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class Label extends JLabel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Timer timer = new Timer(1000/Var.fps, this); //refresh-Zeit
    public void paintComponent(Graphics g) { //initialisiere paintComponent
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(Var.gameState.equals("homescreen") || Var.gameState.equals("startup")) {
            int buttonX = Var.width / 2 - Var.startButtonWidth / 2;
            int buttonY = Var.startButtonY;
            int buttonWidth = Var.startButtonWidth;
            int buttonHeight = Var.images.get("btn_startgame").getHeight() * Var.startButtonWidth / Var.images.get("btn_startgame").getWidth();
            g.drawImage(Var.images.get("Startscreen"), 0, 0, Var.width, Var.height, null);
            if (isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, buttonWidth, buttonHeight) && Var.mouseClicked) {
                g.drawImage(Var.images.get("btn_startgame_pressed"), buttonX, buttonY, buttonWidth, buttonHeight, null);
                Var.startButtonPressed = true;
            } else if (isInsideBox(Var.mouseX, Var.mouseY, buttonX, buttonY, buttonWidth, buttonHeight)) {
                g.drawImage(Var.images.get("btn_startgame_hover"), buttonX, buttonY, buttonWidth, buttonHeight, null);
                if (Var.startButtonPressed) {
                    GameStateHandler.changeGameState("singleplayer");
                    Var.startButtonPressed = false;
                }
            } else {
                g.drawImage(Var.images.get("btn_startgame"), buttonX, buttonY, buttonWidth, buttonHeight, null);
            }
        } else if(Var.gameState.equals("singleplayer") || Var.gameState.equals("pause")) {
            int bgTilesize = (int) ((Var.height * ((100 - (Var.bgBoardPaddingPercent * 2)) / 100)) / Var.tilesY);
            g.drawImage(Var.images.get("Singleplayer"), 0, 0, Var.width, Var.height, null);
            for (int x = 0; x < Var.tilesX; x++) {
                for (int y = 0; y < Var.tilesY; y++) {
                    int bgBoardTilePositionX = Var.width / 2 - (Var.tilesX / 2) * bgTilesize + x * bgTilesize;
                    int bgBoardTilePositionY = (int) (y * bgTilesize + (Var.bgBoardPaddingPercent / 100) * Var.height);

                    if (x == 0 && y == 0) {
                        g.drawImage(Var.images.get("bg_corner_1"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (y == 0 && x == Var.tilesX - 1) {
                        g.drawImage(Var.images.get("bg_corner_2"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (y == Var.tilesY - 1 && x == 0) {
                        g.drawImage(Var.images.get("bg_corner_4"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (y == Var.tilesY - 1 && x == Var.tilesX - 1) {
                        g.drawImage(Var.images.get("bg_corner_3"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (x == 0) {
                        g.drawImage(Var.images.get("bg_side_1"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (x == Var.tilesX - 1) {
                        g.drawImage(Var.images.get("bg_side_3"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (y == 0) {
                        g.drawImage(Var.images.get("bg_side_2"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else if (y == Var.tilesY - 1) {
                        g.drawImage(Var.images.get("bg_side_4"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    } else {
                        g.drawImage(Var.images.get("bg_inside"), bgBoardTilePositionX, bgBoardTilePositionY, bgTilesize, bgTilesize, null);
                    }
                }
            }
            for (int i = 0; i < Var.itemBoxCount; i++) {
                if (i == 0) {
                    g.drawImage(Var.images.get("item_box"),
                            Var.width / 2 + ((Var.tilesX / 2) * bgTilesize) + Var.itemBoxPadding,
                            (int) ((Var.bgBoardPaddingPercent / 100) * Var.height),
                            Var.itemBoxSize,
                            Var.itemBoxSize, null);
                    g.drawImage(Var.images.get(Var.bag.get(i) + "_Brick"),
                            Var.width / 2 + ((Var.tilesX / 2) * bgTilesize) + Var.itemBoxPadding + (Var.itemBoxSize / 2 - (int) (Var.itemBoxSize * Var.itemScalingFactor) / 2),
                            (int) ((Var.bgBoardPaddingPercent / 100) * Var.height) + (Var.itemBoxSize / 2 - (int) (Var.itemBoxSize * Var.itemScalingFactor) / 2),
                            (int) (Var.itemBoxSize * Var.itemScalingFactor),
                            (int) (Var.itemBoxSize * Var.itemScalingFactor), null);
                } else {
                    g.drawImage(Var.images.get("item_box"),
                            Var.width / 2 + ((Var.tilesX / 2) * bgTilesize) + Var.itemBoxPadding,
                            (int) ((Var.bgBoardPaddingPercent / 100) * Var.height) + Var.itemBoxSize + (i - 1) * (int) (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor) + i * Var.itemBoxPadding,
                            (int) (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor),
                            (int) (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor), null);
                    g.drawImage(Var.images.get(Var.bag.get(i) + "_Brick"),
                            (int) (Var.width / 2.0 + ((Var.tilesX / 2.0) * bgTilesize) + Var.itemBoxPadding + (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor / 2 - (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor * Var.itemScalingFactor) / 2)),
                            (int) (((Var.bgBoardPaddingPercent / 100) * Var.height) + Var.itemBoxSize + (i - 1) * (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor) + i * Var.itemBoxPadding + (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor / 2 - (int) (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor * Var.itemScalingFactor) / 2.0)),
                            (int) (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor * Var.itemScalingFactor),
                            (int) (Var.itemBoxSize * Var.secondaryItemBoxScalingFactor * Var.itemScalingFactor), null);
                }
            }
            g.drawImage(Var.images.get("item_box"),
                    Var.width / 2 - ((Var.tilesX / 2) * bgTilesize) - Var.itemBoxPadding - Var.itemBoxSize,
                    (int) ((Var.bgBoardPaddingPercent / 100) * Var.height),
                    Var.itemBoxSize,
                    Var.itemBoxSize, null);
            if(Var.storedTetromino != 'x') {
                g.drawImage(Var.images.get(Var.storedTetromino + "_Brick"),
                        Var.width / 2 - ((Var.tilesX / 2) * bgTilesize) - Var.itemBoxPadding - Var.itemBoxSize + (Var.itemBoxSize / 2 - (int) (Var.itemBoxSize * Var.itemScalingFactor) / 2),
                        (int) ((Var.bgBoardPaddingPercent / 100) * Var.height) + (Var.itemBoxSize / 2 - (int) (Var.itemBoxSize * Var.itemScalingFactor) / 2),
                        (int) (Var.itemBoxSize * Var.itemScalingFactor),
                        (int) (Var.itemBoxSize * Var.itemScalingFactor), null);
            }

            for (int x = 0; x < Var.tilesX; x++) {
                for (int y = 0; y < Var.tilesY; y++) {
                    if (Var.spielfeld[x][y].getTileTexture() != null) {
//                            System.out.println((Var.tileStartY + y * (Var.tileSize + Var.tilePadding)));
                        g.drawImage(Var.spielfeld[x][y].getTileTexture(), Var.tileStartX + x * (Var.tileSize + Var.tilePadding), Var.tileStartY + Var.tilesY - (y * (Var.tileSize + Var.tilePadding)), Var.tileSize, Var.tileSize, null);

                    }

                }
            }
            if (Var.gameState.equals("pause")) {
                g.drawImage(Var.images.get("Pausescreen"), 0, 0, Var.width, Var.height, null);
            }
        }



        timer.start(); // Start Refresh Timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private boolean isInsideBox(int testX, int testY, int x, int y, int width, int height) {
        return testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height);
    }
}
