package Game;

import java.awt.*;

public class Box {

    public Color c; //Farbcode des Blockes; kann abgeändert falls nötig (Textur etc.)
    public int id; //ID des Blockes
    public boolean rotationpoint; //um die box, die diesen boolean auf true hat wird rotiert

    public Box(Color c, int id, boolean rotationpoint) {
        this.c = c;
        this.id = id;
        this.rotationpoint = rotationpoint;
    }

}
