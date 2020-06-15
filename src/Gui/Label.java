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
            case "menu":
                int bgTilesize = (int)((Var.height*((100-(Var.bgBoardPaddingPercent*2))/100))/Var.tilesY);
                g.drawImage(Var.imgBackgroundSingleplayer, 0,0,Var.width, Var.height,null);
                for(int x = 0; x<Var.tilesX; x++) {
                    for (int y = 0; y<Var.tilesY; y++) {
                        int bgBoardTilePositionX = Var.width/2-(Var.tilesX/2)*bgTilesize+x*bgTilesize;
                        int bgBoardTilePositionY = (int)(y*bgTilesize+(Var.bgBoardPaddingPercent/100)*Var.height);
                        if(x==0 && y==0){
                            g.drawImage(Var.imgBackgroundTileCornerLeft,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(y==0 && x==Var.tilesX-1) {
                            g.drawImage(Var.imgBackgroundTileCornerUp,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(y==Var.tilesY-1 && x==0) {
                            g.drawImage(Var.imgBackgroundTileCornerDown,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(y==Var.tilesY-1 && x==Var.tilesX-1) {
                            g.drawImage(Var.imgBackgroundTileCornerRight,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(x==0) {
                            g.drawImage(Var.imgBackgroundTileSideLeft,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(x==Var.tilesX-1) {
                            g.drawImage(Var.imgBackgroundTileSideRight,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(y==0) {
                            g.drawImage(Var.imgBackgroundTileSideUp,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }else if(y==Var.tilesY-1) {
                            g.drawImage(Var.imgBackgroundTileSideDown,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        } else {
                            g.drawImage(Var.imgBackgroundTileInside,bgBoardTilePositionX,bgBoardTilePositionY,bgTilesize,bgTilesize,null);
                        }
                    }
                }
                for(int x = 0; x<Var.tilesX; x++) {
                    for (int y = 0; y<Var.tilesY; y++) {
                        if(Var.spielfeld[x][y].getTileTexture()!=null) {
//                            System.out.println((Var.tileStartY + y * (Var.tileSize + Var.tilePadding)));
                            g.drawImage(Var.spielfeld[x][y].getTileTexture(), Var.tileStartX + x * (Var.tileSize + Var.tilePadding), Var.tileStartY + Var.tilesY-(y * (Var.tileSize + Var.tilePadding)), Var.tileSize, Var.tileSize, null);

                        }

                    }
                }
                break;
        }


        timer.start(); // Start Refresh Timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
