/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 1/6/15 10:05 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class Network
{
    private static String TAG = Consts.APP_NAME + "-" + Network.class.getSimpleName();

    public static String getType(Context context)
    {
        TelephonyManager teleMan = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = teleMan.getNetworkType();

        String type = "";

        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                type = "1xRTT";
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                type = "CDMA";
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                type = "EDGE";
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                type = "eHRPD";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                type = "EVDO rev. 0";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                type = "EVDO rev. A";
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                type = "EVDO rev. B";
                break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                type = "GPRS";
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                type = "HSDPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                type = "HSPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                type = "HSPA+";
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                type = "HSUPA";
                break;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                type = "iDen";
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                type = "LTE";
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                type = "UMTS";
                break;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                type = "Unknown";
                break;
            default:
                type = "Unknown: " + String.valueOf(networkType);
                break;
        }

        return getNetworkClass(context) + " - " + type;
    }

    public static String getNetworkClass(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        String networkClass = "";

        if (info == null || !info.isConnected()) {
            networkClass = "?"; //not connected
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            networkClass = "WIFI";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            TelephonyManager teleMan = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int networkType = teleMan.getNetworkType();

            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    networkClass = "2G";
                    break;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    networkClass = "3G";
                    break;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    networkClass = "4G";
                    break;
                default:
                    networkClass = "?";
                    break;
            }
        }

        return networkClass;
    }
}
