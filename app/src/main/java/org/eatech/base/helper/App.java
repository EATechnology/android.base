/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 1/13/15 12:31 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;

public class App
{
    public static String getVersion(Context context) throws PackageManager.NameNotFoundException
    {
        PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

        return pInfo.versionName;
    }

    public static String getVersionShort(
            Context context) throws PackageManager.NameNotFoundException
    {
        String version = getVersion(context);

        return version.substring(0, version.lastIndexOf(" "));
    }

    public static boolean isDev(Context context)
    {
        try {
            return (Utils.getMeta(context).getInt("SHOW_DB_VIEWER") == 1);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static SharedPreferences getSettings(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
