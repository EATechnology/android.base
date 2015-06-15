package org.eatech.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.eatech.base.R;

public class SplashActivity extends AppCompatActivity
{

    private static String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                startMain();
            }

        }, 1000);

    }

    private void startMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}