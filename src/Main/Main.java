package Main;

import Gui.Gui;
import Var.Var;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon(Var.class.getResource("/loading.gif")), SwingConstants.CENTER));
        window.setBounds(0, 0, 300, 200);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        try {
            new JFXPanel();
            new Var();
            Game.GameStateHandler.changeGameState("startup");
            EventQueue.invokeLater(() -> {
                try {
                    Gui frame = new Gui();
                    window.setVisible(false);
                    window.dispose();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        catch (OutOfMemoryError error) {
            window.setVisible(false);
            window.dispose();
            String currentPath= Var.class
                    .getProtectionDomain()
                    .getCodeSource().getLocation()
                    .toURI().getPath()
                    .replace('/', File.separatorChar).substring(1) ;
            if ( args.length == 0 && Runtime.getRuntime().maxMemory()<512*1024*1024) {
                Process p = Runtime.getRuntime().exec("java -Xmx512m -jar \"" +currentPath+"\" restart");
                //output redirect
                new StreamGobbler(p.getInputStream()).start() ;
                new StreamGobbler(p.getErrorStream()).start() ;
                p.waitFor() ;
            }
            else {
                System.out.println("Zu wenig Arbeitsspeicher!");
                JOptionPane.showMessageDialog(null, "Zu wenig Arbeitsspeicher!", "ERROR" , JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
}
