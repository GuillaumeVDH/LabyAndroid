package fges.rizomm.labyrinthe.components;

import android.graphics.Color;
import android.graphics.RectF;

import java.util.Enumeration;

/**
 * Created by GuillaumeVDH
 */
public abstract class ABlock {
    public static final float SIZE = Ball.RADIUS*2;
    public static enum Type { START, END, TRAP, HOLE }

    protected float _X = 0;
    protected float _Y = 0;
    protected RectF _rectangle = null;
    protected int _color = Color.WHITE;
    protected Type _type = null;

    public ABlock(int x, int y){
        if(x >= 0 && y >= 0)
        {
            this._X = x;
            this._Y = y;
            this._rectangle = new RectF(x * SIZE, y * SIZE, (x+1) * SIZE, (y+1) * SIZE);
        } else
            throw new IllegalArgumentException("Class Start - Bad arguments received -> " + x + " " + y );
    }

    public RectF getRectangle() { return _rectangle; }
    public int getColor()     { return _color; }
    public Type getType()      { return _type; }

    public void setColor(int color) {
        _color = color;
    }

    public void setType(Type type) {
        _type = type;
    }

}
