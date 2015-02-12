package fges.rizomm.labyrinthe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import fges.rizomm.labyrinthe.components.ABlock;
import fges.rizomm.labyrinthe.components.Ball;
import fges.rizomm.labyrinthe.engine.GameEngine;
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
    public Dialog onCreateDialog (int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(id) {
            case 1:
                _gameEngine.stopSensor();
                builder.setCancelable(false)
                        .setTitle(getString(R.string.game_victory_title))
                        .setMessage(getString(R.string.game_victory_text))
                        .setNeutralButton(getString(R.string.game_victory_button), new DialogInterface.OnClickListener() {
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
                        .setTitle(getString(R.string.game_defeat_title))
                        .setMessage(getString(R.string.game_defeat_text))
                        .setNeutralButton(getString(R.string.game_defeat_button), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                _gameEngine.reset();
                                _gameEngine.resume();
                            }
                        });
        }
        return builder.create();
    }

    @Override
    public void onPrepareDialog (int id, Dialog box) {
        // When a dialog box is called
        _gameEngine.stopSensor();
    }
}
