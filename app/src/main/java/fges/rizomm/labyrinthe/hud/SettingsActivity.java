package fges.rizomm.labyrinthe.hud;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fges.rizomm.labyrinthe.R;

public class SettingsActivity extends Activity implements View.OnClickListener  {
    private Button mValidateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mValidateButton = (Button) findViewById(R.id.settings_button_validate);
        mValidateButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
    public void onClick(View v) {
        if(v.getId() == R.id.settings_button_validate)
        {
            //take the value from the edittext
            EditText editText = (EditText) findViewById(R.id.settings_Ball_Speed);
            if (editText.getText().toString().matches("")) {
                Toast.makeText(this, "You did not enter a value", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                int ball_speed = Integer.valueOf(editText.getText().toString());

                //put it in Sharedpref
                SharedPreferences sharedPreferences_ball_speed = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPreferences_ball_speed.edit();
                editor.putInt("ball_speed", ball_speed);
                editor.commit();
                this.finish();
            }
        }
    }

}
