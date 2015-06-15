/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/13/15 8:56 PM
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Time;

import java.util.Date;

public class DateTime
{
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS   = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS    = 24 * HOUR_MILLIS;

    public static String getTimeAgo(long pubDate, Context ctx)
    {
        Time now = new Time();
        now.setToNow();

        return DateUtils.getRelativeTimeSpanString(pubDate, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
    }

    public static String getTimeAgo(Date pubDate, Context ctx)
    {
        return getTimeAgo(pubDate.getTime(), ctx);
    }
}