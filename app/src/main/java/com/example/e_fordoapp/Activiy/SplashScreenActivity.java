package com.example.e_fordoapp.Activiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.e_fordoapp.R;
import com.example.e_fordoapp.Utility.Utility;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    Utility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        utility = new Utility(this);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressbar_id);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                progressbar_work();
                starting_screen();
            }
        });
        thread.start();
    }

    public void progressbar_work()
    {
        for(progress=20; progress<=100; progress=progress+20)
        {
            try {
                Thread.sleep(1000); // value will change gradually after 1s
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void starting_screen() // will start this screen after splash screen
    {
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}