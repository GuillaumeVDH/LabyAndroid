package fges.rizomm.labyrinthe.components;

import android.graphics.Color;

/**
 * Created by GuillaumeVDH
 */
public class Trap extends ABlock {
    /*
    This class allow you to create a Trap.
    If the ball meet it, she will bounce to move away!
    */

    public Trap(int x, int y)
    {
        super(x,y);
        _color = Color.BLACK;
        _type = Type.WALL;
    }

}
