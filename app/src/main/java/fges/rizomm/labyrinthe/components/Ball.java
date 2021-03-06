package fges.rizomm.labyrinthe.components;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by GuillaumeVDH
 */
public class Ball {
    /*
    This class allow you to create a ball.
    Using the accelerometer, the player will be able to deplace it into the game until he reachs the end.
     */

    //Radius of the ball
    public static final int RADIUS = 30;

    //Position
    private float _X;
    private float _Y;

    //Speed
    private float _speedX;
    private float _speedY;
    private float _maxSpeed;
    //private static final float  = 1.5f;
    private static final float BOUNCE = 1f;
    private static final float SPEED_REDUCER = 0.3f;

    //Color
    private int _color = Color.BLUE;

    //Others
    private RectF _rStart = null;
    private RectF _rCollision = null;
    private int _screenWidth = 0;
    private int _screenHeight = 0;

    /*
       END OF ATTRIBUTES DECLARATIONS
   ------------------------------------------------------------------------------------------------
       START OF METHODS DECLARATIONS
    */

    public Ball()
    {
        _rCollision = new RectF();
        _maxSpeed = 1f;
    }


    //Init the position of the ball
    public void initStart(RectF start)
    {
        this._rStart = start;
        this._X = start.left + RADIUS;
        this._Y = start.top + RADIUS;
    }

    // GETTERS
    public float getX()         {   return this._X; }
    public float getY()         {   return this._Y; }
    public float getSpeedX()    {   return this._speedX; }
    public float getSpeedY()    {   return this._speedY; }
    public RectF getRCollision(){   return this._rCollision; }


    //SETTERS
    public void setX(float x)
    {
        if(x < RADIUS) //Bounce
        {
            _X = RADIUS;
            _speedY = -_speedY  / BOUNCE;
        } else if(x > _screenWidth - RADIUS)
        {
            _X = _screenWidth - RADIUS;
            _speedY = -_speedY / RADIUS;
        } else
            _X = x;
    }

    public void setMaxSpeed(int value)
    {
        if(value > 0 && value <= 100)
        {
            _maxSpeed = value;
        } else {
            throw new IllegalArgumentException("Bad maximum speed value for the ball in settings. Must be between 1 and 100. Applying default value = 1");
        }
    }

    public void setY(float y)
    {
        if(y < RADIUS) //Bounce
        {
            _Y = RADIUS;
            _speedX = -_speedX  / BOUNCE;
        } else if(y > _screenHeight - RADIUS)
        {
            _Y = _screenHeight - RADIUS;
            _speedX = -_speedX / RADIUS;
        } else
            _Y = y;
    }

    /* SPEED */
    // Called when boucing against an horizontal object
    private void switchSpeedX()
    {
        _speedX = -_speedX;
    }

    // Called when boucing against a vertical object
    private void switchSpeedY()
    {
        _speedY = -_speedY;
    }

    /* UPDATE */
    public RectF updatePosition(float x, float y)
    {
        _speedX += x / SPEED_REDUCER;
        _speedY += y / SPEED_REDUCER;

        if(_speedX > _maxSpeed)
            _speedX = _maxSpeed;
        else if(_speedX < -_maxSpeed)
            _speedX = -_maxSpeed;

        if(_speedY > _maxSpeed)
            _speedY = _maxSpeed;
        else if(_speedY < -_maxSpeed)
            _speedY = -_maxSpeed;

        this.setX(_X + _speedY);
        this.setY(_Y + _speedX);
        _rCollision.set(_X - RADIUS, _Y - RADIUS, _X + RADIUS, _Y + RADIUS);

        return _rCollision;
    }

    public void resetBall()
    {
        _speedX = 0;
        _speedY = 0;
        this._X = _rStart.left + RADIUS;
        this._Y = _rStart.top + RADIUS;
    }

    public void Bounce(float previousX, float previousY) {
        _X = previousX;
        _Y = previousY;

        _speedX = - Math.abs(_speedX);
        _speedY = - Math.abs(_speedY);
    }

    /* SCREEN SIZE */
    public void setHeight(int height) {
        _screenHeight = height;
    }

    public void setWidth(int width) {
        _screenWidth = width;
    }
}
