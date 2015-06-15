/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 6/9/15 5:26 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.eatech.base.helper.Consts;
import org.eatech.base.helper.MyNotification;
import org.json.JSONException;
import org.json.JSONObject;

public class GcmIntentService extends IntentService
{
    private static String TAG = Consts.APP_NAME + "-" + GcmIntentService.class.getSimpleName();

    public GcmIntentService()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        if (!extras.isEmpty()) {
            if (extras.getString("message") != null) {
                try {
                    String message = extras.getString("message");
                    JSONObject data = new JSONObject(message);

                    Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.i(TAG, data.toString());
                    Log.i(TAG, "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

                    if (data.has("notification_type")) {
                        MyNotification myNotification = new MyNotification(this);
                        int notification_type = data.getInt("notification_type");
                        String version;

                        switch (notification_type) {
                            case Consts.NOTIFICATION_DEFAULT:
                                String title = data.getString("title");
                                String description = data.getString("description");

                                myNotification.sendSimple(notification_type, title, description);
                                break;
                            case Consts.NOTIFICATION_UPDATE_GPLAY:
                                version = data.getString("version");

                                myNotification.sendGooglePlayUpdating(version);
                                break;
                            case Consts.NOTIFICATION_UPDATE_YSTORE:
                                version = data.getString("version");

                                myNotification.sendYandexStoreUpdating(version);
                                break;
                        }
                    }
                } catch (PackageManager.NameNotFoundException | JSONException e) {
                }
            }
        }

        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}