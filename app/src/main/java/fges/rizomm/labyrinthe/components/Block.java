package fges.rizomm.labyrinthe.components;

import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
abstract class Block {
    public static final float SIZE = Ball.RADIUS*2;

    protected float _X = 0;
    protected float _Y = 0;

    protected RectF _rectangle = null;

    protected RectF getRectangle()
    {
        return _rectangle;
    }
}
