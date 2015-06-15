/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 6/9/15 2:59 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.gcm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.eatech.base.BuildConfig;
import org.eatech.base.helper.Consts;
import org.eatech.base.helper.Prefs;

import java.io.IOException;

public class MyPlayService
{
    private static String TAG = Consts.APP_NAME + "-" + MyPlayService.class.getSimpleName();

    private static volatile MyPlayService instance;

    public static MyPlayService getInstance()
    {
        MyPlayService localInstance = instance;
        if (localInstance == null) {
            synchronized (MyPlayService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MyPlayService();
                }
            }
        }
        return localInstance;
    }

    public int isAvailable(AppCompatActivity activity)
    {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                //                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                //                        Consts.PLAY_SERVICES_RESOLUTION_REQUEST).show();

                return 0;
            } else {

                return -1;
            }
        }

        return 1;
    }

    public void startRegistration(AppCompatActivity activity)
    {
        int availability = this.isAvailable(activity);

        switch (availability) {
            case -1:
                Toast.makeText(activity, "This device is not supported.", Toast.LENGTH_LONG).show();

                activity.finish();
                break;
            case 0:
                oldRegistration(activity);
                break;
            case 1:
                oldRegistration(activity);
                //                Intent intent = new Intent(activity, MyRegistrationIntentService.class);
                //                activity.startService(intent);
                break;
        }
    }

    private void oldRegistration(final AppCompatActivity activity)
    {
        new AsyncTask()
        {

            @Override
            protected Object doInBackground(Object[] params)
            {
                try {
                    String token = GoogleCloudMessaging
                            .getInstance(activity)
                            .register(Consts.GCM_SENDER_ID);

                    showToken(token, activity);

                } catch (IOException e) {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        }.execute(null, null, null);
    }

    public void showToken(String regId, AppCompatActivity activity)
    {
        if (regId == null || regId.length() == 0) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(TAG, "REG_ID: UNDEFINED");
            Log.e(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

            return;
        }

        Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Log.i(TAG, "REG_ID: " + regId);
        Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        String savedRegId = Prefs.getPrefs(activity).getString(Consts.PREFERENCES_REG_ID, null);
    }
}
