package fges.rizomm.labyrinthe.components;

import android.graphics.Color;
import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
public class Hole extends ABlock {
    /*
    This class allow you to create a Trap.
    When the ball roll over a trap, the ball fall into it. It's a game over!
     */

    public Hole(int x, int y)
    {
        super(x,y);
        _color = Color.GRAY;
        _type = Type.TRAP;
    }

}
