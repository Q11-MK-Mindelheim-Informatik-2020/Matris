package Gui;

import java.awt.*;
import Var.Var;

import javax.swing.*;

public class DrawArea extends JPanel {
    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Hintergrund
        g2.fillRect(0, 0, getWidth(), getHeight());

        for(int i = 0; i < Var.n; i++) {
            for(int j = Var.m-1; j >= 0; j--) {
//                g2.setColor(Var.spielfeld[i][j].getC());
                g2.fillRect(i*40,(Var.m-1-j)*40, 40,40);
            }
        }

        repaint();

    }
}
