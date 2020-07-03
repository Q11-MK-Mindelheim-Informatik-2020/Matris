package Gui;
import javax.swing.*;

import Game.GameStateHandler;
import Game.KeyHandler;
import Var.Var;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Gui extends JFrame implements MouseListener, MouseMotionListener {
    //initialisiere JFrame
    public Gui() {
        Label lb;
        setResizable(true);
        setTitle("justTetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Var.width, Var.height);
        setLocationRelativeTo(null);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        lb = new Label();
        add(lb);
        addKeyListener(new KeyHandler());
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt) {
                Component c = (Component)evt.getSource();
                Var.width = getContentPane().getWidth();
                Var.height = getContentPane().getHeight();
            }
        });
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                if(Var.gameState.equals("pause")) {
                    GameStateHandler.changeGameState("singleplayer");
                }
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                if(Var.gameState.equals("singleplayer")) {
                    GameStateHandler.changeGameState("pause");
                }
            }
        });
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
        Var.mouseY = e.getY()-26;
        //System.out.println("x: " + e.getX() + " y: " + e.getY());
    }
}