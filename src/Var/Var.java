package Var;
import javax.swing.*;
import java.awt.*;

import Game.Box;

public class Var {
    public static int currentid = 1; //ID des aktiven/fallenden Blöcken
    public static int m = 10; //Höhe
    public static int n = 7; //Breite
    public static Box spielfeld[][] = new Box[n][m]; //Spielfeld mit Box-Objekten
    public static int width = 500, height = 800;
    public static Color backgroundColor = new Color(255, 255, 255);
}
