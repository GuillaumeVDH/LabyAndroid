package fges.rizomm.labyrinthe.components;

import android.graphics.RectF;
import android.os.Debug;

import java.io.Console;

/**
 * Created by GuillaumeVDH
 */
public class Start extends ABlock {
    /*
    This class allow you to create a Start place for the ball.
    We can have only one start by level.
     */

    public Start(int x, int y)
    {
        if(x >= 0 && y >= 0)
        {
            _X = x;
            _Y = y;
            _rectangle = new RectF(_X * SIZE, _Y * SIZE, (_X+1) * SIZE, (_Y+1) * SIZE);
        } else
            throw new IllegalArgumentException("Class Start - Bad arguments received -> " + x + " " + y );
    }
}
