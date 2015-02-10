package fges.rizomm.labyrinthe.hud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

import fges.rizomm.labyrinthe.components.ABlock;
import fges.rizomm.labyrinthe.components.Ball;
import fges.rizomm.labyrinthe.components.End;
import fges.rizomm.labyrinthe.components.Start;
import fges.rizomm.labyrinthe.components.Trap;


/**
 * Created by GuillaumeVDH
 */
public class GameHUD extends SurfaceView implements SurfaceHolder.Callback {

    private Ball _ball;
    private GameDraw _thread;
    private SurfaceHolder _surfaceHolder;
    private Paint _paint;
    private List<ABlock> _blocks = null;
    /*
       END OF ATTRIBUTES DECLARATIONS
   ------------------------------------------------------------------------------------------------
       START OF METHODS DECLARATIONS
    */

    public GameHUD(Context context) {
        super(context);
        _surfaceHolder = getHolder();
        _surfaceHolder.addCallback(this);
        _thread = new GameDraw();

        _paint = new Paint();
        _paint.setStyle(Paint.Style.FILL);

        _ball = new Ball();
    }
    public Ball getBall() { return _ball; }
    public void setBall(Ball ball) {
        _ball = ball;
    }

    public List<ABlock> getBlocks() { return _blocks; }
    public void setBlocks(List<ABlock> blocks) {
        _blocks = blocks;
    }

    /**
     * This is called immediately after the surface is first created.
     * Implementations of this should start up whatever rendering code
     * they desire.  Note that only one thread can ever draw into
     * a {@link android.view.Surface}, so you should not draw into the Surface here
     * if your normal rendering will be in another thread.
     *
     * @param holder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread.setDraw(true);
        _thread.start();
        if(_ball != null ) {
            _ball.setHeight(getHeight());
            _ball.setHeight(getWidth());
        }
    }

    /**
     * This is called immediately after any structural changes (format or
     * size) have been made to the surface.  You should at this point update
     * the imagery in the surface.  This method is always called at least
     * once, after {@link #surfaceCreated}.
     *
     * @param holder The SurfaceHolder whose surface has changed.
     * @param format The new PixelFormat of the surface.
     * @param width  The new width of the surface.
     * @param height The new height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * This is called immediately before a surface is being destroyed. After
     * returning from this call, you should no longer try to access this
     * surface.  If you have a rendering thread that directly accesses
     * the surface, you must ensure that thread is no longer touching the
     * Surface before returning from this function.
     *
     * @param holder The SurfaceHolder whose surface is being destroyed.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        _thread.setDraw(false);
        boolean retry = true;
        while(retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);

        //Draw the game
        if(_blocks != null)
        {
            for(ABlock block : _blocks)
            {
                if (block instanceof Start) {
                    _paint.setColor(Color.WHITE);
                } else if (block instanceof End) {
                    _paint.setColor(Color.RED);
                } else if (block instanceof Trap) {
                    _paint.setColor(Color.BLACK);
                }
                canvas.drawRect(block.getRectangle(), _paint);
            }
        }

        //Draw the ball
        if(_ball != null) {
            _paint.setColor(Color.MAGENTA);
            canvas.drawCircle(_ball.getX(), _ball.getX(), _ball.RADIUS, _paint);
        }
    }


    private class GameDraw extends Thread {
        private boolean _draw = true;

    /*
        END OF ATTRIBUTES DECLARATIONS
    ------------------------------------------------------------------------------------------------
        START OF METHODS DECLARATIONS
    */
        public boolean getDraw() { return _draw; }

        public void setDraw(boolean draw) {
            _draw = draw;
        }

        @Override
        public void run() {
            Canvas canvas;
            while(_draw) {
                canvas = null;

                try {
                    canvas = _surfaceHolder.lockCanvas();
                    synchronized (_surfaceHolder) {
                        onDraw(canvas);
                    }
                } finally {
                    if(canvas != null)
                        _surfaceHolder.unlockCanvasAndPost(canvas);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e ) {}
            }
        }
    }
}
