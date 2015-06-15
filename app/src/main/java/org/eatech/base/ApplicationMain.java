/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/12/15 11:32 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

public class ApplicationMain extends Application
{
    private static Context context;

    HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public static Context getContext()
    {
        return context;
    }

    public enum TrackerName
    {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
    }

    public ApplicationMain()
    {
        super();
    }

    public synchronized Tracker getTracker()
    {
        TrackerName trackerId = TrackerName.GLOBAL_TRACKER;

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);

        Tracker t = analytics.newTracker(R.xml.global_tracker);
        mTrackers.put(trackerId, t);

        return mTrackers.get(trackerId);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        context = getApplicationContext();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }
}