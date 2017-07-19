package com.example.vadym.coupbatterystatusapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

/**
 * This class represents Asynchronous shared preferences saving process
 */
public class PreferenceUtils {

    protected static final String PREF_FILE_NAME = "com.example.vadym.coupbatterystatusapp";

    public static void saveString(final Context context, String key, String value) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                SharedPreferences.Editor editor = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE).edit();
                editor.putString(params[0], params[1]);
                editor.commit();
                return null;
            }
        }.execute(key, value);
    }

    public static String loadString(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }


}
