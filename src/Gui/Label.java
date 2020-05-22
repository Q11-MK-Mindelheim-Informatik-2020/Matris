package Gui;

import Var.Var;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Label extends JLabel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Timer timer = new Timer(20, this); //refresh-Zeit
    public void paintComponent(Graphics g) { //initialisiere paintComponent
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Var.backgroundColor); // Hintergrundfarbe setzen
        g.fillRect(0, 0, Var.width, Var.height); // Hintergrund zeichnen
        timer.start(); // Start Refresh Timer
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
