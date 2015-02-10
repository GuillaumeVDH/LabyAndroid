package fges.rizomm.labyrinthe.engine;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

import fges.rizomm.labyrinthe.GameActivity;
import fges.rizomm.labyrinthe.components.Ball;
import fges.rizomm.labyrinthe.components.ABlock;
import fges.rizomm.labyrinthe.components.End;
import fges.rizomm.labyrinthe.components.Start;
import fges.rizomm.labyrinthe.components.Trap;

/**
 * Created by GuillaumeVDH
 */
public class GameEngine {
    private Ball _ball = null;
    private List<ABlock> _blocks = new ArrayList<ABlock>();
    private GameActivity _activity = null;

    //Init sensors
    private SensorManager _sensorManager = null;
    private Sensor _sensor = null;


     /*
        END OF ATTRIBUTES DECLARATIONS
    ------------------------------------------------------------------------------------------------
        START OF METHODS DECLARATIONS
     */

    public GameEngine(GameActivity view)     {
        _activity = view;
        _sensorManager = (SensorManager) _activity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        _sensor = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public Ball getBall() { return _ball; }
    public void setBall(Ball ball) {
        _ball = ball;
    }

    public void reset() {
        _ball.resetBall();
    }

    public void stopSensor() {
        _sensorManager.unregisterListener(_sensorEventListener, _sensor);
    }

    public void resume() {
        _sensorManager.registerListener(_sensorEventListener, _sensor, _sensorManager.SENSOR_DELAY_GAME);
    }

    SensorEventListener _sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];

            if(_ball != null) {
                //Update the position of the ball
                _ball.updatePosition(x, y);
                RectF collision = _ball.getRCollision();


                for (ABlock block : _blocks) {

                    RectF tmp = new RectF(block.getRectangle());
                    if (tmp.intersect(collision)) {
                        if (block instanceof End) {
                            _activity.showDialog(1); //TODO
                        } else if (block instanceof Trap) {
                            _activity.showDialog(0); //TODO
                        }
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public List<ABlock> buildGame() {
        _blocks = new ArrayList<ABlock>();

        _blocks.add(new Trap(0, 0));
        _blocks.add(new Trap(0, 1));
        _blocks.add(new Trap(0, 2));
        _blocks.add(new Trap(0, 3));
        _blocks.add(new Trap(0, 4));
        _blocks.add(new Trap(0, 5));
        _blocks.add(new Trap(0, 6));
        _blocks.add(new Trap(0, 7));
        _blocks.add(new Trap(0, 8));
        _blocks.add(new Trap(0, 9));
        _blocks.add(new Trap(0, 10));
        _blocks.add(new Trap(0, 11));
        _blocks.add(new Trap(0, 12));
        _blocks.add(new Trap(0, 13));

        _blocks.add(new Trap(1, 0));
        _blocks.add(new Trap(1, 13));

        _blocks.add(new Trap(2, 0));
        _blocks.add(new Trap(2, 13));

        _blocks.add(new Trap(3, 0));
        _blocks.add(new Trap(3, 13));

        _blocks.add(new Trap(4, 0));
        _blocks.add(new Trap(4, 1));
        _blocks.add(new Trap(4, 2));
        _blocks.add(new Trap(4, 3));
        _blocks.add(new Trap(4, 4));
        _blocks.add(new Trap(4, 5));
        _blocks.add(new Trap(4, 6));
        _blocks.add(new Trap(4, 7));
        _blocks.add(new Trap(4, 8));
        _blocks.add(new Trap(4, 9));
        _blocks.add(new Trap(4, 10));
        _blocks.add(new Trap(4, 13));

        _blocks.add(new Trap(5, 0));
        _blocks.add(new Trap(5, 13));

        _blocks.add(new Trap(6, 0));
        _blocks.add(new Trap(6, 13));

        _blocks.add(new Trap(7, 0));
        _blocks.add(new Trap(7, 1));
        _blocks.add(new Trap(7, 2));
        _blocks.add(new Trap(7, 5));
        _blocks.add(new Trap(7, 6));
        _blocks.add(new Trap(7, 9));
        _blocks.add(new Trap(7, 10));
        _blocks.add(new Trap(7, 11));
        _blocks.add(new Trap(7, 12));
        _blocks.add(new Trap(7, 13));

        _blocks.add(new Trap(8, 0));
        _blocks.add(new Trap(8, 5));
        _blocks.add(new Trap(8, 9));
        _blocks.add(new Trap(8, 13));

        _blocks.add(new Trap(9, 0));
        _blocks.add(new Trap(9, 5));
        _blocks.add(new Trap(9, 9));
        _blocks.add(new Trap(9, 13));

        _blocks.add(new Trap(10, 0));
        _blocks.add(new Trap(10, 5));
        _blocks.add(new Trap(10, 9));
        _blocks.add(new Trap(10, 13));

        _blocks.add(new Trap(11, 0));
        _blocks.add(new Trap(11, 5));
        _blocks.add(new Trap(11, 9));
        _blocks.add(new Trap(11, 13));

        _blocks.add(new Trap(12, 0));
        _blocks.add(new Trap(12, 1));
        _blocks.add(new Trap(12, 2));
        _blocks.add(new Trap(12, 3));
        _blocks.add(new Trap(12, 4));
        _blocks.add(new Trap(12, 5));
        _blocks.add(new Trap(12, 9));
        _blocks.add(new Trap(12, 8));
        _blocks.add(new Trap(12, 13));

        _blocks.add(new Trap(13, 0));
        _blocks.add(new Trap(13, 8));
        _blocks.add(new Trap(13, 13));

        _blocks.add(new Trap(14, 0));
        _blocks.add(new Trap(14, 8));
        _blocks.add(new Trap(14, 13));

        _blocks.add(new Trap(15, 0));
        _blocks.add(new Trap(15, 8));
        _blocks.add(new Trap(15, 13));

        _blocks.add(new Trap(16, 0));
        _blocks.add(new Trap(16, 4));
        _blocks.add(new Trap(16, 5));
        _blocks.add(new Trap(16, 6));
        _blocks.add(new Trap(16, 7));
        _blocks.add(new Trap(16, 8));
        _blocks.add(new Trap(16, 9));
        _blocks.add(new Trap(16, 13));

        _blocks.add(new Trap(17, 0));
        _blocks.add(new Trap(17, 13));

        _blocks.add(new Trap(18, 0));
        _blocks.add(new Trap(18, 13));

        _blocks.add(new Trap(19, 0));
        _blocks.add(new Trap(19, 1));
        _blocks.add(new Trap(19, 2));
        _blocks.add(new Trap(19, 3));
        _blocks.add(new Trap(19, 4));
        _blocks.add(new Trap(19, 5));
        _blocks.add(new Trap(19, 6));
        _blocks.add(new Trap(19, 7));
        _blocks.add(new Trap(19, 8));
        _blocks.add(new Trap(19, 9));
        _blocks.add(new Trap(19, 10));
        _blocks.add(new Trap(19, 11));
        _blocks.add(new Trap(19, 12));
        _blocks.add(new Trap(19, 13));

        Start blockStart = new Start(2, 2);
        _ball.initStart(new RectF(blockStart.getRectangle()));
        _blocks.add(blockStart);

        _blocks.add(new End(8, 11));

        return _blocks;
    }
}
