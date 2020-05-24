package Gui;
import javax.swing.*;

import Game.KeyHandler;
import Var.Var;
import Gui.Label;

@SuppressWarnings("serial")
public class Gui extends JFrame {
    //initialisiere JFrame
    public Gui() {
        Label lb;
        setResizable(false);
        setTitle("justTetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, Var.width, Var.height);
        setLocationRelativeTo(null);
        lb = new Label();
        lb.setBounds(0, 0, Var.width, Var.height);
        add(lb);
        addKeyListener(new KeyHandler());
    }
}