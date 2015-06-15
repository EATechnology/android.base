package org.eatech.base.activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.eatech.base.R;
import org.eatech.base.ga.Events;

public class SettingsActivity extends AppCompatActivity
{

    private static String TAG = SettingsActivity.class.getSimpleName();

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        getFragmentManager().beginTransaction()
                .replace(R.id.container_body, new SettingsFragment())
                .commit();

        Events.screenName(this, getString(R.string.screen_settings));
    }

    // http://codetheory.in/saving-user-settings-with-android-preferences/
    public static class SettingsFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences_feed);
        }
    }
}