package Gui;
import javax.swing.*;

import Game.GameStateHandler;
import Var.Var;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("serial")
public class Gui extends JFrame implements MouseListener, MouseMotionListener {
    //initialisiere JFrame

    private static HashMap<String, Timer> timers = new HashMap<>();
    private static HashSet<Integer> locked = new HashSet<>();

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

        addKeyBinding(lb, KeyEvent.VK_LEFT, "left", false, e -> Game.Move.left());
        addKeyBinding(lb, KeyEvent.VK_RIGHT, "right", false, e -> Game.Move.right());
        addKeyBinding(lb, KeyEvent.VK_DOWN, "down", false, e -> Game.Move.down());
        addKeyBinding(lb, KeyEvent.VK_UP, "downdown", true, e -> Game.Move.downdown());
        addKeyBinding(lb, KeyEvent.VK_ENTER, "rotate", true, e -> Game.Move.rotate());
        addKeyBinding(lb, KeyEvent.VK_CONTROL, KeyEvent.CTRL_MASK, "store", true, e -> Game.Move.store());
        addKeyBinding(lb, KeyEvent.VK_ESCAPE, "pause", true, e -> Game.Mechanics.pause());

        add(lb);
        //addKeyListener(new KeyHandler());
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

    private static void addKeyBinding(JComponent comp, int keyCode, String id, boolean once, ActionListener actionListener) {
        addKeyBinding(comp, keyCode, 0, id, once, actionListener);
    }

    private static void addKeyBinding(JComponent comp, int keyCode, int modifier, String id, boolean once, ActionListener actionListener) {
        if (!once) {
            timers.put(id, new Timer(30, actionListener));
            timers.get(id).setInitialDelay(300);
        }

        InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();

        im.put(KeyStroke.getKeyStroke(keyCode, modifier, false), id);
        im.put(KeyStroke.getKeyStroke(keyCode, 0, true), id + "Released");

        ap.put(id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!once && !timers.get(id).isRunning()) {
                    actionListener.actionPerformed(e);
                    timers.get(id).start();
                }
                else if (once && !locked.contains(keyCode)) {
                    actionListener.actionPerformed(e);
                    locked.add(keyCode);
                }
            }
        });
        ap.put(id + "Released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!once && timers.get(id).isRunning()) {
                    timers.get(id).stop();
                }
                else if (once) {
                    locked.remove(keyCode);
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