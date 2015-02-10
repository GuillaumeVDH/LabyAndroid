package fges.rizomm.labyrinthe.components;

import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
public class Trap extends ABlock {
    /*
    This class allow you to create a Trap.
    When the ball roll over a trap, the ball fall into it. It's a game over!
     */

    public Trap(int x, int y)
    {
        if(x >= 0 && y >= 0)
        {
            _X = x;
            _Y = y;
            _rectangle = new RectF(_X * SIZE, _Y * SIZE, (_X+1) * SIZE, (_Y+1) * SIZE);
        } else
            throw new IllegalArgumentException("Class Trap - Bad arguments received -> " + x + " " + y );
    }
}
