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
import fges.rizomm.labyrinthe.components.Hole;
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
                float oldX = _ball.getX();
                float oldY =_ball.getY();
                //Update the position of the ball
                RectF collision = _ball.updatePosition(x,y);

                for (ABlock block : _blocks) {

                    RectF tmp = new RectF(block.getRectangle());
                    if (tmp.intersect(collision)) {
                        if (block.getType() == ABlock.Type.END) {
                            _activity.showDialog(1);
                        } else if (block.getType() == ABlock.Type.HOLE) {
                            _activity.showDialog(0);
                        } else if (block.getType() == ABlock.Type.TRAP) {
                            _ball.Bounce(oldX, oldY);
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

        _blocks.add(new Hole(0, 0));
        _blocks.add(new Hole(0, 1));
        _blocks.add(new Hole(0, 2));
        _blocks.add(new Hole(0, 3));
        _blocks.add(new Hole(0, 4));
        _blocks.add(new Hole(0, 5));
        _blocks.add(new Hole(0, 6));
        _blocks.add(new Hole(0, 7));
        _blocks.add(new Hole(0, 8));
        _blocks.add(new Hole(0, 9));
        _blocks.add(new Hole(0, 10));
        _blocks.add(new Hole(0, 11));
        _blocks.add(new Hole(0, 12));
        _blocks.add(new Hole(0, 13));
        _blocks.add(new Hole(0, 14));

        _blocks.add(new Hole(1, 0));
        _blocks.add(new Trap(1, 11));
        _blocks.add(new Trap(1, 12));
        _blocks.add(new Trap(1, 13));
        _blocks.add(new Hole(1, 14));

        _blocks.add(new Hole(2, 0));
        _blocks.add(new Hole(2, 14));

        _blocks.add(new Hole(3, 0));
        _blocks.add(new Hole(3, 14));

        _blocks.add(new Hole(4, 0));
        _blocks.add(new Hole(4, 1));
        _blocks.add(new Hole(4, 2));
        _blocks.add(new Hole(4, 3));
        _blocks.add(new Trap(4, 4));
        _blocks.add(new Trap(4, 7));
        _blocks.add(new Hole(4, 8));
        _blocks.add(new Hole(4, 9));
        _blocks.add(new Hole(4, 10));
        _blocks.add(new Hole(4, 11));
        _blocks.add(new Hole(4, 14));

        _blocks.add(new Hole(5, 0));
        _blocks.add(new Hole(5, 14));

        _blocks.add(new Hole(6, 0));
        _blocks.add(new Hole(6, 14));

        _blocks.add(new Hole(7, 0));
        _blocks.add(new Hole(7, 4));
        _blocks.add(new Hole(7, 5));
        _blocks.add(new Hole(7, 6));
        _blocks.add(new Hole(7, 7));
        _blocks.add(new Hole(7, 8));
        _blocks.add(new Hole(7, 9));
        _blocks.add(new Hole(7, 10));
        _blocks.add(new Hole(7, 11));
        _blocks.add(new Trap(7, 12));
        _blocks.add(new Trap(7, 13));
        _blocks.add(new Hole(7, 14));

        _blocks.add(new Hole(8, 0));
        _blocks.add(new Hole(8, 4));
        _blocks.add(new Hole(8, 8));
        _blocks.add(new Hole(8, 11));
        _blocks.add(new Hole(8, 12));
        _blocks.add(new Hole(8, 13));
        _blocks.add(new Hole(8, 14));

        _blocks.add(new Hole(9, 0));
        _blocks.add(new Hole(9, 4));
        _blocks.add(new Hole(9, 7));
        _blocks.add(new Hole(9, 8));
        _blocks.add(new Hole(9, 14));

        _blocks.add(new Hole(10, 0));
        _blocks.add(new Hole(10, 4));
        _blocks.add(new Hole(10, 14));

        _blocks.add(new Hole(11, 0));
        _blocks.add(new Hole(11, 3));
        _blocks.add(new Hole(11, 4));
        _blocks.add(new Hole(11, 11));
        _blocks.add(new Hole(11, 14));

        _blocks.add(new Hole(12, 0));
        _blocks.add(new Hole(12, 3));
        _blocks.add(new Hole(12, 7));
        _blocks.add(new Hole(12, 8));
        _blocks.add(new Hole(12, 9));
        _blocks.add(new Hole(12, 10));
        _blocks.add(new Hole(12, 11));
        _blocks.add(new Hole(12, 14));

        _blocks.add(new Hole(13, 0));
        _blocks.add(new Hole(13, 3));
        _blocks.add(new Hole(13, 11));
        _blocks.add(new Hole(13, 14));

        _blocks.add(new Hole(14, 0));
        _blocks.add(new Hole(14, 3));
        _blocks.add(new Hole(14, 4));
        _blocks.add(new Hole(14, 11));
        _blocks.add(new Hole(14, 14));

        _blocks.add(new Hole(15, 0));
        _blocks.add(new Hole(15, 4));
        _blocks.add(new Hole(15, 8));
        _blocks.add(new Hole(15, 9));
        _blocks.add(new Hole(15, 10));
        _blocks.add(new Hole(15, 11));
        _blocks.add(new Hole(15, 14));

        _blocks.add(new Hole(16, 0));
        _blocks.add(new Hole(16, 8));
        _blocks.add(new Hole(16, 14));

        _blocks.add(new Hole(17, 0));
        _blocks.add(new Hole(17, 8));
        _blocks.add(new Hole(17, 14));

        _blocks.add(new Hole(18, 0));
        _blocks.add(new Trap(18, 2));
        _blocks.add(new Hole(18, 8));
        _blocks.add(new Hole(18, 11));
        _blocks.add(new Trap(18, 12));
        _blocks.add(new Trap(18, 13));
        _blocks.add(new Hole(18, 14));

        _blocks.add(new Hole(19, 0));
        _blocks.add(new Hole(19, 1));
        _blocks.add(new Hole(19, 2));
        _blocks.add(new Hole(19, 3));
        _blocks.add(new Hole(19, 4));
        _blocks.add(new Hole(19, 5));
        _blocks.add(new Hole(19, 6));
        _blocks.add(new Hole(19, 7));
        _blocks.add(new Hole(19, 8));
        _blocks.add(new Hole(19, 9));
        _blocks.add(new Hole(19, 10));
        _blocks.add(new Hole(19, 11));
        _blocks.add(new Hole(19, 12));
        _blocks.add(new Hole(19, 13));
        _blocks.add(new Hole(19, 14));

        Start blockStart = new Start(2, 2);
        _ball.initStart(new RectF(blockStart.getRectangle()));
        _blocks.add(blockStart);

        _blocks.add(new End(18, 9));

        return _blocks;
    }
}
