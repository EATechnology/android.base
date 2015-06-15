/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 1/8/15 1022 PM
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import org.eatech.base.R;
import org.json.JSONObject;

public class Device
{
    public static JSONObject info(Context context)
    {
        JSONObject info = new JSONObject();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        try {
            info.put("board", android.os.Build.BOARD);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("brand", android.os.Build.BRAND);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("build_id", android.os.Build.ID);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("device", android.os.Build.DEVICE);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_build", android.os.Build.DISPLAY);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_density", metrics.density);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_density_dpi", metrics.densityDpi);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_scaled_density", metrics.scaledDensity);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_xdpi", metrics.xdpi);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_ydpi", metrics.ydpi);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_density_default", DisplayMetrics.DENSITY_DEFAULT);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_density_low", DisplayMetrics.DENSITY_LOW);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_density_medium", DisplayMetrics.DENSITY_MEDIUM);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_density_high", DisplayMetrics.DENSITY_HIGH);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_height_pixels", metrics.heightPixels);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("display_width_pixels", metrics.widthPixels);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("finger", android.os.Build.FINGERPRINT);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("inc", android.os.Build.VERSION.INCREMENTAL);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("locale", context.getResources().getConfiguration().locale.toString());
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("manufacturer", android.os.Build.MANUFACTURER);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("model", android.os.Build.MODEL);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("product", android.os.Build.PRODUCT);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("release", android.os.Build.VERSION.RELEASE);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("sdk", android.os.Build.VERSION.SDK_INT);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("serial", android.os.Build.SERIAL);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("tags", android.os.Build.TAGS);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("time", android.os.Build.TIME);
        } catch (Exception e) {
            // miss exception
        }

        try {
            info.put("user", android.os.Build.USER);
        } catch (Exception e) {
            // miss exception
        }

        return info;
    }

    public static boolean isTablet(Context context)
    {
        return (context.getResources().getConfiguration().screenLayout
            & Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static String getType(Context context)
    {
        return context.getResources().getBoolean(R.bool.isTablet) ? "Tablet" : "Mobile";
    }
}
