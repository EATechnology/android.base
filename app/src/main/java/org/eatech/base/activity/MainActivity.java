package org.eatech.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.Button;

import org.eatech.base.R;
import org.eatech.base.ga.Events;
import org.eatech.base.gcm.MyPlayService;
import org.eatech.base.helper.App;
import org.eatech.base.helper.Consts;
import org.eatech.base.model.Categories;
import org.eatech.base.model.CategoryItem;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener
{
    private static String TAG = Consts.APP_NAME + "-" + MainActivity.class.getSimpleName();

    private Button         mBtnSettings   = null;
    private Toolbar        mToolbar       = null;
    private FragmentDrawer drawerFragment = null;
    private DrawerLayout   drawer_layout  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawer_layout, mToolbar);
        drawerFragment.setDrawerListener(this);

        mBtnSettings = (com.rey.material.widget.Button) findViewById(R.id.btnSettings);
        mBtnSettings.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                drawer_layout.closeDrawers();

                new Handler().postDelayed(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivityForResult(intent, Consts.REQUEST_SETTINGS_ACTIVITY);
                    }
                }, 300);
            }
        });

        displayView(0);

        MyPlayService.getInstance().startRegistration(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Consts.REQUEST_SETTINGS_ACTIVITY:
                drawer_layout.closeDrawers();
                displayView(0);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (!App.isDev(this)) {
            menu.removeItem(R.id.action_db);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_about:
                Dialog.Builder builder = new SimpleDialog.Builder(R.style.SimpleDialogLight)
                        .contentView(R.layout.about)
                        .positiveAction("OK");

                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getSupportFragmentManager(), null);

                Events.aboutOpen(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);

        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                Events.startApp(MainActivity.this);
            }

        }, 100);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    public void onDrawerItemSelected(View view, int position)
    {
        displayView(position);
    }

    private void displayView(int position)
    {
        Fragment fragment = null;
        String title;
        String slug;

        try {
            CategoryItem category = Categories.getAll().get(position);

            title = category.getTitle();
            slug = category.getSlug();

            fragment = PostListFragment.newInstance(title, slug);
        } catch (IndexOutOfBoundsException e) {
            title = getString(R.string.app_name);
        }

        if (fragment != null) {
            drawer_layout.closeDrawers();
            getSupportActionBar().setTitle(title);

            final Fragment finalFragment = fragment;
            new Handler().postDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, finalFragment);
                    fragmentTransaction.commit();

                }
            }, 300);
        }
    }
}