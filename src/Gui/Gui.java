package Gui;
import javax.swing.*;

import Game.KeyHandler;
import Var.Var;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class Gui extends JFrame implements MouseListener, MouseMotionListener {
    //initialisiere JFrame
    public Gui() {
        Label lb;
        setResizable(false);
        setTitle("justTetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, Var.width, Var.height+25);
        setLocationRelativeTo(null);
        lb = new Label();
        lb.setBounds(0, 0, Var.width, Var.height);
        add(lb);
        addKeyListener(new KeyHandler());
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Var.mouseClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Var.mouseClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Var.mouseX = e.getX();
        Var.mouseY = e.getY();
    }
}