package Gui;

import Var.Var;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Label extends JLabel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Timer timer = new Timer(1000/Var.fps, this); //refresh-Zeit

    public void paintComponent(Graphics g) {

        //initialisiere paintComponent
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Startscreen
        if(Var.gameState.equals("homescreen") || Var.gameState.equals("startup")) {

            // Draw Startscreen
            DrawHelper.drawBackgroundImage("Startscreen", g);
            DrawHelper.startButton.drawButton("gamestate", "singleplayer", 400, Var.width/2, Var.height/2, g);
        // Singleplayer
        } else if(Var.gameState.equals("options1")) {
            /*
            OPTIONEN

            fps
            Größe des Spielfelds (m und n)
            ghostmode
            ARR und DAS
            Themes ?

            Music
            Lautstärke der Musik
            Soundeffekts an aus
            Lautstärke der Soundeffects
            * */

            // Draw Background
            DrawHelper.drawBackgroundImage("Singleplayer", g);
            DrawHelper.drawCheckbox("Ghostmodus", Var.fontColor, Var.test, 100, 300, 200, 50, "Comic Sans MS",() -> Var.test = !Var.test, g);
            Var.m = (int)DrawHelper.drawSlider("Breite: "+Var.m, Var.fontColor, Var.m, Var.width/7, (Var.width/5)*2, 450, Var.width/20, Var.width/3, 2, 0, 30, "Comic Sans MS", g);
            Var.n = (int)DrawHelper.drawSlider("Länge:  "+Var.n, Var.fontColor, Var.n, Var.width/7, (Var.width/5)*2, 550, Var.width/20, Var.width/3, 2, 0, 30, "Comic Sans MS", g);
        } else if(Var.gameState.equals("singleplayer") || Var.gameState.equals("pause") || Var.gameState.equals("gameover")) {

            // Draw Background
            DrawHelper.drawBackgroundImage("Singleplayer", g);

            // Draw Score-elements
            DrawHelper.drawText("Punktestand: " + Var.score, 20,40, Var.fontColor, 30, "Comic Sans MS", g);
            DrawHelper.drawText("Linien: "+ Var.linecounter, 20,80, Var.fontColor,30, "Comic Sans MS", g);
            DrawHelper.drawText("Level "+ Var.level, 20,120, Var.fontColor,30, "Comic Sans MS", g);

            // Draw Board
            DrawHelper.drawBackgroundTiles(Var.tilesX, Var.tilesY, Var.padding, g);

            // Draw Itemboxes
            DrawHelper.drawItemBoxes(Var.itemBoxSize, Var.padding, Var.tilesX, Var.tilesY, Var.itemScalingFactor, Var.itemBoxCount, g);

            // Draw Tetrominos
            DrawHelper.drawTetrominos(Var.tilePadding, Var.tilesX, Var.tilesY, Var.padding, g);

            // Draw Pausescreen
            if (Var.gameState.equals("pause")) {
                DrawHelper.drawBackgroundImage("Pausescreen", g);
                DrawHelper.restartButton.drawButton("restart", null, 400, Var.width/2, Var.height-Var.height/4, g);
            }
            // Draw Gameover-screen
            else if (Var.gameState.equals("gameover")) {
                DrawHelper.drawBackgroundImage("Gameoverscreen", g);
                DrawHelper.restartButton.drawButton("restart", null, 400, Var.width/2, Var.height-Var.height/4, g);
            }
        }
        timer.start(); // Start Refresh Timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    } // Repaint Timer
}
