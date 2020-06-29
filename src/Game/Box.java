package Game;

import Var.Var;

import java.awt.image.BufferedImage;

public class Box {

    private int id; //ID des Blockes
    private boolean rotationpoint; //um die Box mit diesem Boolean auf true wird rotiert
    private BufferedImage tileTexture;

    Box(BufferedImage tileTexture, int id, boolean rotationpoint) {
        this.tileTexture = tileTexture;
        this.id = id;
        this.rotationpoint = rotationpoint;
    }
    Box(BufferedImage tileTexture, int id) {
        this.tileTexture = tileTexture;
        this.id = id;
        this.rotationpoint = false;
    }
    Box(BufferedImage tileTexture) {
        this.tileTexture = tileTexture;
        this.id = Var.currentid;
        this.rotationpoint = false;
    }
    Box(BufferedImage tileTexture, boolean rotationpoint) {
        this.tileTexture = tileTexture;
        this.id = Var.currentid;
        this.rotationpoint = rotationpoint;
    }
    public Box(int id) {
        this.tileTexture = null;
        this.id = id;
        this.rotationpoint = false;
    }

    public BufferedImage getTileTexture() {
        return tileTexture;
    }
    int getId() {
        return id;
    }
    boolean getRotationpoint() {
        return rotationpoint;
    }


}
