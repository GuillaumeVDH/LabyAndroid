package fges.rizomm.labyrinthe.components;

import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
public abstract class ABlock {
    public static final float SIZE = Ball.RADIUS*2;

    protected float _X = 0;
    protected float _Y = 0;

    protected RectF _rectangle = null;

    public ABlock(int x, int y){
        if(x >= 0 && y >= 0)
        {
            this._X = x;
            this._Y = y;
            this._rectangle = new RectF(x * SIZE, y * SIZE, (x+1) * SIZE, (y+1) * SIZE);
        } else
            throw new IllegalArgumentException("Class Start - Bad arguments received -> " + x + " " + y );
    }

    public RectF getRectangle()
    {
        return _rectangle;
    }
}
