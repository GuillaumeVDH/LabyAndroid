package fges.rizomm.labyrinthe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import fges.rizomm.labyrinthe.components.ABlock;
import fges.rizomm.labyrinthe.components.Ball;
import fges.rizomm.labyrinthe.engine.GameEngine;
import fges.rizomm.labyrinthe.hud.GameDialog;
import fges.rizomm.labyrinthe.hud.GameHUD;


public class GameActivity extends Activity {

    private GameEngine _gameEngine = null;
    private GameHUD _gameHUD = null;
    private List<ABlock> _blocks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _gameHUD = new GameHUD(this);
        setContentView(_gameHUD);

        _gameEngine = new GameEngine(this);

        Ball ball = new Ball();
        _gameHUD.setBall(ball);
        _gameEngine.setBall(ball);


        _blocks = _gameEngine.buildGame();
        _gameHUD.setBlocks(_blocks);

    }

    @Override
    protected void onResume() {
        super.onResume();
        _gameEngine.resume();
    }

    @Override
    protected void onPause() {
        super.onStop();
        _gameEngine.stopSensor();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Dialog onCreateDialog (int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(id) {
            case 1:
                _gameEngine.stopSensor();
                builder.setCancelable(false)
                        .setTitle("VICTORY!")
                        .setMessage("You succeed avoing all the traps! ")
                        .setNeutralButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                _gameEngine.reset();
                                _gameEngine.resume();
                            }
                        });
                break;

            case 0:
                _gameEngine.stopSensor();
                builder.setCancelable(false)
                        .setTitle("GAME OVER!")
                        .setMessage("You're not yet ready for victory. But keep up, give another try!")
                        .setNeutralButton("Try again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                _gameEngine.reset();
                                _gameEngine.resume();
                            }
                        });
        }
        return builder.create();
    }
}
