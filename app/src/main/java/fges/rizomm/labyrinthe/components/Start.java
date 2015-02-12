package fges.rizomm.labyrinthe.components;

import android.graphics.Color;
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
        super(x,y);
        _color = Color.TRANSPARENT;
        _type = Type.START;
    }
}
