/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/12/15 11:08 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class Utils
{
    public static boolean isConnected(Context context)
    {
        if (context == null) {
            return false;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = connectivityManager.getActiveNetworkInfo();

        if (i == null) {
            return false;
        }

        if (!i.isConnected()) {
            return false;
        }

        if (!i.isAvailable()) {
            return false;
        }

        return true;

    }

    public static String getTrackingID(Context context) throws PackageManager.NameNotFoundException
    {
        return getMeta(context).getString("GOOGLE_ANALYTICS_ID");
    }

    public static Bundle getMeta(Context context) throws PackageManager.NameNotFoundException
    {
        ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

        return ai.metaData;
    }
}
