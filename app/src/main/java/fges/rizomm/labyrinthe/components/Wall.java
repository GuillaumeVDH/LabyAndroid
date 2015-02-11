package fges.rizomm.labyrinthe.components;

import android.graphics.Color;

/**
 * Created by GuillaumeVDH
 */
public class Wall extends ABlock {
    /*
    This class allow you to create a Wall.
    If the ball meet it, she will bounce to move away!
    */

    public Wall(int x, int y)
    {
        super(x,y);
        _color = Color.BLACK;
        _type = Type.WALL;
    }

}
