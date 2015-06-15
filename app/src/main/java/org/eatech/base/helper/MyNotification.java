/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 6/10/15 3:40 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import org.eatech.base.BuildConfig;
import org.eatech.base.R;
import org.eatech.base.activity.MainActivity;

public class MyNotification
{
    private static String TAG = Consts.APP_NAME + "-" + MyNotification.class.getSimpleName();

    public Uri                        mSoundUri;
    public Context                    mContext;
    public NotificationManager        mNotificationManager;
    public NotificationCompat.Builder mNotificationBuilder;

    public MyNotification(Context context)
    {
        this.mContext = context;

        this.mNotificationManager = (NotificationManager) mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);

        this.mSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        this.mNotificationBuilder = new NotificationCompat.Builder(mContext)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notification_app);
    }

    public void sendSimple(int notification_id, String title, String description)
    {
        sendSimple(notification_id, title, description, false);
    }

    public void sendSimple(int notification_id, String title, String description, boolean with_sound)
    {
        Intent mIntent = new Intent(mContext, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent
                .getActivity(mContext, 0, mIntent, PendingIntent.FLAG_ONE_SHOT);

        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mNotificationBuilder
                .setContentTitle(title)
                .setContentText(description)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(description))
                .setContentIntent(mPendingIntent);

        if (with_sound) {
            mNotificationBuilder.setSound(mSoundUri);
        }

        mNotificationManager.notify(notification_id, mNotificationBuilder.build());
    }

    public void sendGooglePlayUpdating(String version) throws PackageManager.NameNotFoundException
    {
        String versionName = BuildConfig.VERSION_NAME.replace("-debug", "");
        if (versionName.equals(version)) {
            return;
        }

        String title = "Обновление \"" + mContext.getString(R.string.app_name);
        String description = "Доступна новая версия \"" + mContext.getString(R.string.app_name) + "\" в Google Play";

        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.eatech.base"));
        PendingIntent mPendingIntent = PendingIntent
                .getActivity(mContext, 0, mIntent, PendingIntent.FLAG_ONE_SHOT);

        mNotificationBuilder
                .setContentTitle(title)
                .setContentText(description)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(description))
                .setContentIntent(mPendingIntent)
                .setSmallIcon(R.drawable.ic_google_play);

        mNotificationBuilder.setSound(mSoundUri);
        mNotificationManager.notify(Consts.NOTIFICATION_UPDATE_GPLAY, mNotificationBuilder.build());
    }

    public void sendYandexStoreUpdating(String version)
    {
        String versionName = BuildConfig.VERSION_NAME.replace("-debug", "");
        if (versionName.equals(version)) {
            return;
        }

        String title = "Обновление \"" + mContext.getString(R.string.app_name);
        String description = "Доступна новая версия \"" + mContext.getString(R.string.app_name) + "\" в Yandex.Store";

        Uri address = Uri.parse("yastore://details?id=org.eatech.base");
        Intent mIntent = new Intent(Intent.ACTION_VIEW, address);
        PendingIntent mPendingIntent = PendingIntent
                .getActivity(mContext, 0, mIntent, PendingIntent.FLAG_ONE_SHOT);

        mNotificationBuilder
                .setContentTitle(title)
                .setContentText(description)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(description))
                .setContentIntent(mPendingIntent)
                .setSmallIcon(R.drawable.ic_yastore);

        mNotificationBuilder.setSound(mSoundUri);
        mNotificationManager.notify(Consts.NOTIFICATION_UPDATE_YSTORE, mNotificationBuilder.build());
    }
}
