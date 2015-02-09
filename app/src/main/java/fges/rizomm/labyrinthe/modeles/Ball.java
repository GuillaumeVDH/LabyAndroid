package fges.rizomm.labyrinthe.modeles;

import android.graphics.Color;

/**
 * Created by GuillaumeVDH
 */
public class Ball {
    //Radius of the ball
    public static final int RADIUS = 10;

    //Position
    private float _X;
    private float _Y;

    //Speed
    private float _speedX;
    private float _speedY;
    private static final float MAX_SPEED = 20f;

    //Color
    private int _color = Color.BLUE;
}
