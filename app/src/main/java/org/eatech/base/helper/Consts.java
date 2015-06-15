/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/7/15 3:28 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

public class Consts
{
    public static final String APP_NAME = "15MINUT";

    public static final String RSS_URL      = "http://15minut.org";
    public static final String RSS_URL_FULL = "http://15.eatechnologies.org";
    public static final String EXCHANGE_URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    public static final String API_VERSION = "v1";
    public static final String API_HOST    = "http://api.gcm.eatechnologies.org";
    public static final String API_URL     = API_HOST + "/" + API_VERSION;

    public static final String GCM_SENDER_ID             = "485076453307";
    public static final String GCM_SERVER_API_KEY        = "AIzaSyBwub6CPqfA39goy6bn9aq2fFyQ3RAFSjo";
    public static final String GCM_SENT_TOKEN_TO_SERVER  = "sentTokenToServer";
    public static final String GCM_REGISTRATION_COMPLETE = "registrationComplete";

    public static final int REQUEST_SETTINGS_ACTIVITY = 1000;

    public static final int NOTIFICATION_DEFAULT       = 2000;
    public static final int NOTIFICATION_UPDATE_GPLAY  = 2001;
    public static final int NOTIFICATION_UPDATE_YSTORE = 2002;

    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String SETTINGS_SHOW_IMAGES = "FeedShowImages";

    public static final String MOBPARTNER_DEFAULT_POOL_ID             = "64285";
    public static final String MOBPARTNER_MAIN_POOL_ID                = "64303";
    public static final int    MOBPARTNER_POST_OPENS_TOADINTERSTITIAL = 5;

    public static final String PREFERENCES_NAME            = APP_NAME + "PREFS";
    public static final String PREFERENCES_POST_OPEN_COUNT = PREFERENCES_NAME + "POSTOPENSCOUNT";
    public static final String PREFERENCES_REG_ID          = PREFERENCES_NAME + "REGID";
    public static final String PREFERENCES_API_TOKEN       = PREFERENCES_NAME + "APITOKEN";
    public static final String PREFERENCES_API_USER_ID     = PREFERENCES_NAME + "APIUSERID";
}
