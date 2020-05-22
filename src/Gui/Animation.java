package Gui;

import Var.Var;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {
    public static int iterableAnimation = 0;

    // kann ignoriert werden, nur ein Test >.>
    public static void bgAnimation(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(iterableAnimation <= 360) {
                    Label label = new Label();
                    Var.backgroundColor = Color.getHSBColor(50, iterableAnimation, 50);
                    label.repaint();
                    iterableAnimation++;
                } else {
                    iterableAnimation = 0;
                }

            }
        }, 0, 200);
    }

}
