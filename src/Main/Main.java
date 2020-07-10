package Main;

import Gui.Gui;
import Var.Var;
import javafx.embed.swing.JFXPanel;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        new JFXPanel();
        new Var();
        Game.GameStateHandler.changeGameState("startup");
        EventQueue.invokeLater(() -> {
            try {
                Gui frame = new Gui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
