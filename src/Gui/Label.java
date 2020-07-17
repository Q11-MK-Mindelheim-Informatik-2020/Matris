package Gui;

import Var.Var;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


public class Label extends JLabel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Timer timer = new Timer(1000/Var.fps, this); //refresh-Zeit
    private static DecimalFormat dF = new DecimalFormat("#.#");

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void paintComponent(Graphics g) {

        /*
        music setting
         */

        //initialisiere paintComponent
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Startscreen
        switch (Var.gameState) {
            case "homescreen":
            case "startup":

                // Draw Startscreen
                DrawHelper.drawBackgroundImage("Startscreen", g);
                DrawHelper.startButton.drawButton("gamestate", "singleplayer", 400, Var.width / 2, (Var.height / 10) * 6, g);
                DrawHelper.optButton.drawButton("gamestate", "options1", 400, Var.width / 2, (Var.height / 10) * 8, g);
                DrawHelper.helpButton.drawButton("gamestate", "controls", 100, Var.width - 55, 50, g);

                // Singleplayer
                break;
            case "options1":

                DrawHelper.drawBackgroundImage("Singleplayer", g);
                DrawHelper.drawCheckbox("Ghostmodus:", Var.fontColor, Var.ghostmode, Var.width / 7, (int) ((Var.width / 5) * 2.5), (Var.height / 15) * 2, Var.width / 30, "Comic Sans MS", () -> Var.ghostmode = !Var.ghostmode, g);
                Var.tilesY = (int) DrawHelper.drawSlider("Höhe: " + Var.tilesY, Var.fontColor, Var.tilesY, Var.width / 7, (int) ((Var.width / 5) * 2.5), (Var.height / 15) * 3, Var.width / 30, Var.width / 3, 1, 6, 30, "Comic Sans MS", g);
                Var.tilesX = (int) DrawHelper.drawSlider("Breite:  " + Var.tilesX, Var.fontColor, Var.tilesX, Var.width / 7, (int) ((Var.width / 5) * 2.5), (Var.height / 15) * 5, Var.width / 30, Var.width / 3, 1, 4, 15, "Comic Sans MS", g);
                Var.m = Var.tilesY + 4;
                Var.n = Var.tilesX;
                Var.ARR = round(DrawHelper.drawSlider("ARR: " + Var.ARR, Var.fontColor, Var.ARR, Var.width / 7, (int) ((Var.width / 5) * 2.5), (Var.height / 15) * 7, Var.width / 30, Var.width / 3, 0.1, 0, 6, "Comic Sans MS", g), 1);
                Var.DAS = round(DrawHelper.drawSlider("DAS:  " + Var.DAS, Var.fontColor, Var.DAS, Var.width / 7, (int) ((Var.width / 5) * 2.5), (Var.height / 15) * 9, Var.width / 30, Var.width / 3, 0.1, 0, 20, "Comic Sans MS", g), 1);
                Var.volume = (int) DrawHelper.drawSlider("Lautstärke:  " + Var.volume, Var.fontColor, Var.volume, Var.width / 7, (int) ((Var.width / 5) * 2.5), (Var.height / 15) * 11, Var.width / 30, Var.width / 3, 1, 0, 100, "Comic Sans MS", g);
                DrawHelper.enterButton.drawButton("gamestate", "startup", 300, Var.width / 2, (Var.height / 15) * 13, g);

                break;
            case "controls":

                DrawHelper.drawBackgroundImage("Singleplayer", g);
                DrawHelper.drawBackgroundImage("controls", g);
                DrawHelper.enterButton.drawButton("gamestate", "startup", 300, Var.width / 2, (Var.height / 15) * 14, g);

                break;
            case "singleplayer":
            case "pause":
            case "gameover":

                // Draw Background
                //DrawHelper.drawBackgroundImage("Singleplayer", g);
                DrawHelper.drawBackgroundImage("Singleplayer" + Var.currentBackgroundID, g);

                // Draw Score-elements
                g.drawImage(Var.images.get("bg_text"), 0, Var.height - (60 + Var.width / 30 * 3), Var.width / 4, 60 + Var.width / 30 * 3, null);
                DrawHelper.drawText("Punktestand: " + Var.score, 20, Var.height - (40 + Var.width / 30 * 2), Var.fontColor, Var.width / 40, "Comic Sans MS", g);
                DrawHelper.drawText("Linien: " + Var.linecounter, 20, Var.height - (40 + Var.width / 30 * 2) + Var.width / 30, Var.fontColor, Var.width / 40, "Comic Sans MS", g);
                DrawHelper.drawText("Level " + Var.level, 20, Var.height - (40 + Var.width / 30 * 2) + Var.width / 30 * 2, Var.fontColor, Var.width / 40, "Comic Sans MS", g);

                // Draw Board
                DrawHelper.drawBackgroundTiles(Var.tilesX, Var.tilesY, Var.padding, g);

                // Draw Itemboxes
                DrawHelper.drawItemBoxes((int) ((Var.itemBoxSize / 100.0) * Var.width), Var.padding, Var.tilesX, Var.tilesY, Var.itemScalingFactor, Var.itemBoxCount, g);

                // Draw Tetrominos
                DrawHelper.drawTetrominos(Var.tilePadding, Var.tilesX, Var.tilesY, Var.padding, g);
                // Draw Pausescreen
                if (Var.gameState.equals("pause")) {
                    DrawHelper.drawBackgroundImage("Pausescreen", g);
                    DrawHelper.restartButton.drawButton("restart", null, 400, Var.width / 2, (Var.height / 20) * 10, g);
                    DrawHelper.resButton.drawButton("gamestate", "singleplayer", 400, Var.width / 2, (Var.height / 20) * 14, g);
                    DrawHelper.menuButton.drawButton("gamestate", "startup", 400, Var.width / 2, (Var.height / 20) * 18, g);
                }
                // Draw Gameover-screen
                else if (Var.gameState.equals("gameover")) {
                    DrawHelper.drawBackgroundImage("Gameoverscreen", g);
                    DrawHelper.restartButton.drawButton("restart", null, 400, Var.width / 2, Var.height - Var.height / 4, g);
                }
                break;
        }
        timer.start(); // Start Refresh Timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    } // Repaint Timer
}
