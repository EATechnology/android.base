/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 6/11/15 3:51 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs
{
    public static SharedPreferences getPrefs(Context context)
    {
        return context.getSharedPreferences(Consts.PREFERENCES_NAME, context.MODE_PRIVATE);
    }
}
