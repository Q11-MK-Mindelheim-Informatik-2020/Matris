package Game;

import java.awt.*;

public class Box {

    private Color c; //Farbcode des Blockes; kann abgeändert falls nötig (Textur etc.)
    private int id; //ID des Blockes
    private boolean rotationpoint; //um die box, die diesen boolean auf true hat wird rotiert

    public Box(Color c, int id, boolean rotationpoint) {
        this.c = c;
        this.id = id;
        this.rotationpoint = rotationpoint;
    }

    public Color getC() {
        return c;
    }
    public int getId() {
        return id;
    }


}
