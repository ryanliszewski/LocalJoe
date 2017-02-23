package com.singhliszewski.eventbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by monisaini on 12/2/16.
 */

public class SplashScreen extends Activity {

    // Set Duration of the Splash Screen
    long Delay = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the Title Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get the view from splash_screen.xml
        setContentView(R.layout.splash_screen);

        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();

                // Start the LocationService.class
                Intent intent = new Intent(SplashScreen.this, LocationService.class);
                startActivity(intent);
                // Start MainActivity.class
                Intent myIntent = new Intent(SplashScreen.this,
                        CardListActivity.class);
                startActivity(myIntent);
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }
}
