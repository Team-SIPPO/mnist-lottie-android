package io.github.team_sippo.lottie_sample;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String TAG = "AAA";
    private LottieAnimationView animationView = null;
    private LottieSwitchView switchView = null;
    private Integer maxFrame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        animationView = findViewById(R.id.animation_view);

        switchView = findViewById(R.id.animation_view2);
        Toast.makeText(this,String.valueOf(animationView.getMaxFrame()),Toast.LENGTH_LONG).show();
        switchView.setSwitchOnProceed(0f, 0.5f);
        switchView.setSwitchOffProceed(0.5f, 1.0f);
        switchView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Toast.makeText(this, "on", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "off", Toast.LENGTH_LONG).show();
            }
        });

        animationView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Integer endFrame = Double.valueOf(String.valueOf(animationView.getMaxFrame())).intValue();
                if(maxFrame == null){
                    maxFrame = endFrame;
                }

                Log.d(TAG, "pressed fab button." + maxFrame);
                animationView.setMaxFrame(maxFrame/2);
                animationView.playAnimation();
                return false;
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
