package fges.rizomm.labyrinthe.components;

import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
public class End extends Block {
    /*
    This class allow you to create an End for the actual level.
    We can have multiple ends for one level.
     */

    public End(int x, int y)
    {
        if(x >= 0 && y >= 0)
        {
            _X = x;
            _Y = y;
            _rectangle = new RectF(_X * SIZE, _Y * SIZE, (_X+1) * SIZE, (_Y+1) * SIZE);
        } else
            throw new IllegalArgumentException("Class End - Bad arguments received -> " + x + " " + y );
    }
}
