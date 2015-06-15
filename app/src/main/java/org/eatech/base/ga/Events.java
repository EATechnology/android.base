/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 1/13/15 11:54 AM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.ga;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

import org.eatech.base.ApplicationMain;
import org.eatech.base.helper.App;
import org.eatech.base.helper.Consts;
import org.eatech.base.helper.Device;
import org.eatech.base.helper.Network;

import java.util.Map;

public class Events
{
    private static final String TAG = Consts.APP_NAME + "-" + Events.class.getSimpleName();

    private enum Category
    {
        APPLICATION,
        GOOGLE_PLAY,
        DIALOG,
        CATEGORY,
        POST
    }

    private enum Action
    {
        NONE,
        OPEN,
        OPEN_IN_BROWSER,
        OPEN_VIDEO,
        RATE,
        SHARE,
        NETWORK,
        DEVICE,
        VERSION
    }

    // PRIVATE METHODS
    private static Tracker __instance(Activity activity)
    {
        return ((ApplicationMain) activity.getApplication()).getTracker();
    }

    private static Map<String, String> __appBuilder()
    {
        return new HitBuilders
                .AppViewBuilder()
                .build();
    }

    private static Map<String, String> __eventBuilder(String category, String action)
    {
        return new HitBuilders
                .EventBuilder()
                .setCategory(category)
                .setAction(action)
                .build();
    }

    private static Map<String, String> __eventBuilder(String category, String action, String label)
    {
        return new HitBuilders
                .EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build();
    }

    private static Map<String, String> __eventBuilder(String category, String action, String label,
                                                      long value)
    {
        return new HitBuilders
                .EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .setValue(value)
                .build();
    }

    private static void __sendEvent(Activity activity, String category, String action)
    {
        __instance(activity).send(__eventBuilder(category, action));
    }

    private static void __sendEvent(Activity activity, String category, String action, String label)
    {
        __instance(activity).send(__eventBuilder(category, action, label));
    }

    private static void __networkType(Activity activity)
    {
        __sendEvent(activity, Category.APPLICATION.name(), Action.NETWORK.name(), Network.getType(activity));
    }

    private static void __deviceType(Activity activity)
    {
        __sendEvent(activity, Category.APPLICATION.name(), Action.DEVICE.name(), Device.getType(activity));
    }

    private static void __appVersion(Activity activity)
    {
        try {
            String version = App.getVersion(activity);

            __sendEvent(activity, Category.APPLICATION.name(), Action.VERSION.name(), version);
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf(TAG, "App.getVersion FATAL");
        }
    }

    // PUBLIC METHODS
    public static void startApp(Activity activity)
    {
        __networkType(activity);
        __appVersion(activity);
        __deviceType(activity);
    }

    public static void screenName(Activity activity, String screenName)
    {
        Tracker tracker = __instance(activity);

        if (tracker != null) {
            tracker.setScreenName(screenName);
            tracker.send(__appBuilder());
        } else {
            Log.wtf(TAG, "setScreenName: tracker is NULL");
        }
    }

    public static void aboutOpen(Activity activity)
    {
        __sendEvent(activity, Category.DIALOG.name(), Action.OPEN.name(), "About");
    }

    public static void openOtherApps(Activity activity)
    {
        __sendEvent(activity, Category.GOOGLE_PLAY.name(), Action.OPEN.name(), "Other Apps");
    }

    public static void rateApp(Activity activity)
    {
        __sendEvent(activity, Category.GOOGLE_PLAY.name(), Action.RATE.name());
    }

    public static void categoryOpen(Activity activity, String title)
    {
        __sendEvent(activity, Category.CATEGORY.name(), Action.OPEN.name(), title);
    }

    public static void postOpen(Activity activity, String category, String title)
    {
        __sendEvent(activity, Category.POST.name(), Action.OPEN.name(), category + ": " + title);
    }

    public static void postOpenInBrowser(Activity activity, String category, String title)
    {
        __sendEvent(activity, Category.POST.name(), Action.OPEN_IN_BROWSER.name(), category + ": " + title);
    }

    public static void postOpenVideo(Activity activity, String category, String title)
    {
        __sendEvent(activity, Category.POST.name(), Action.OPEN_VIDEO.name(), category + ": " + title);
    }
}
