package Game;

import Var.Var;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Box {

    private int id; //ID des Blockes
    private boolean rotationpoint; //um die box, die diesen boolean auf true hat wird rotiert
    private BufferedImage tileTexture;

    public Box(BufferedImage tileTexture, int id, boolean rotationpoint) {
        this.tileTexture = tileTexture;
        this.id = id;
        this.rotationpoint = rotationpoint;
    }
    public Box(BufferedImage tileTexture, int id) {
        this.tileTexture = tileTexture;
        this.id = id;
        this.rotationpoint = false;
    }
    public Box(BufferedImage tileTexture) {
        this.tileTexture = tileTexture;
        this.id = Var.currentid;
        this.rotationpoint = false;
    }
    public Box(BufferedImage tileTexture, boolean rotationpoint) {
        this.tileTexture = tileTexture;
        this.id = Var.currentid;
        this.rotationpoint = rotationpoint;
    }

    public BufferedImage getTileTexture() {
        return tileTexture;
    }
    public int getId() {
        return id;
    }
    public boolean getRotationpoint() {
        return rotationpoint;
    }


}
