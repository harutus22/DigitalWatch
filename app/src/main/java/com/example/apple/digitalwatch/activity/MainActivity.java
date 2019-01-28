package com.example.apple.digitalwatch.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.example.apple.digitalwatch.R;
import com.example.apple.digitalwatch.customView.DigitWatch;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        run = new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_main);
                handler.postDelayed(run, 250);
            }
        };
        handler = new Handler();
        run.run();


    }
}
