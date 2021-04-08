package com.ekagra.domesticviolencewalkthrough;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Dell on 6/9/2019.
 */

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        final TypeWriter tw = (TypeWriter) findViewById(R.id.name);

        tw.setCharacterDelay(200);
        tw.animateText("made by Ekagra");

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                Intent intent = new Intent(StartActivity.this, Main.class);
                startActivity(intent);
                finish();

            }

        }, 4000);

    }



}
