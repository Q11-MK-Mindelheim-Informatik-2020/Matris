package Gui;

import Var.Var;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Label extends JLabel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Timer timer = new Timer(1000/Var.fps, this); //refresh-Zeit
    public void paintComponent(Graphics g) { //initialisiere paintComponent
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = new Font("Comic Sans MS",Font.PLAIN, 30);
        g.setFont(f);
        if(Var.gameState.equals("homescreen") || Var.gameState.equals("startup")) {
            DrawHelper.drawBackgroundImage("Startscreen", g);
            DrawHelper.startButton.drawButton("gamestate", "singleplayer", 400, Var.width/2, Var.height/2, g);
        } else if(Var.gameState.equals("singleplayer") || Var.gameState.equals("pause") || Var.gameState.equals("gameover")) {
            int bgTilesize = (int) ((Var.height - 2* Var.padding) / Var.tilesY);
            DrawHelper.drawBackgroundImage("Singleplayer", g);
            DrawHelper.drawText("Punktestand: " + Var.score, 20,40, Var.fontColor, g);
            DrawHelper.drawText("Linien: "+ Var.linecounter, 20,80, Var.fontColor, g);
            DrawHelper.drawText("Level "+ Var.level, 20,120, Var.fontColor, g);
            DrawHelper.drawBackgroundTiles(Var.tilesX, Var.tilesY, Var.padding, g);
            DrawHelper.drawItemBoxes(Var.itemBoxSize, Var.padding, Var.tilesX, Var.tilesY, Var.itemScalingFactor, Var.itemBoxCount, g);
            DrawHelper.drawTetrominos(Var.tilePadding, Var.tilesX, Var.tilesY, Var.padding, g);
            if (Var.gameState.equals("pause")) {
                DrawHelper.drawBackgroundImage("Pausescreen", g);
                DrawHelper.restartButton.drawButton("restart", null, 400, Var.width/2, Var.height-Var.height/4, g);
            }
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
    }
}
