package fges.rizomm.labyrinthe.components;

import android.graphics.Color;
import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
public class End extends ABlock {
    /*
    This class allow you to create an End for the actual level.
    We can have multiple ends for one level.
     */

    public End(int x, int y)
    {
        super(x,y);
        _color = Color.parseColor("#D32F2F");
        _type = Type.END;
    }
}
