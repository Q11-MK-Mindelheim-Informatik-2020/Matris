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

        switch(Var.gameState) {
            case "startup":
                g.drawImage(Var.imgBackgroundSingleplayer, 0,0,Var.width, Var.height,null);
                for(int x = 0; x<Var.tilesX; x++) {
                    for (int y = 0; y<Var.tilesY; y++) {
                        if(Var.spielfeld[x][y].getTileTexture()!=null) {
//                            System.out.println((Var.tileStartY + y * (Var.tileSize + Var.tilePadding)));
                            g.drawImage(Var.spielfeld[x][y].getTileTexture(), Var.tileStartX + x * (Var.tileSize + Var.tilePadding), Var.tileStartY + Var.tilesY-(y * (Var.tileSize + Var.tilePadding)), Var.tileSize, Var.tileSize, null);

                        }

                    }
                }
                break;
            case "menu":
                g.setColor(Color.CYAN); // Hintergrundfarbe setzen
                g.fillRect(0, 0, Var.width, Var.height); // Hintergrund zeichnen
                break;
        }


        timer.start(); // Start Refresh Timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
