package com.example.nitesh.payu.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 8/27/2017.
 */

public class PreferenceManager {

    private SharedPreferences sharedPrefs;
    private static PreferenceManager sInstance;
    private SharedPreferences.Editor editor;
    private static Context context;


    public interface PreferenceKeys {
        String SHARED_PREFS = "PayU";
        String IS_FIRST_LAUNCH = "is_first_launch";
    }


    public static PreferenceManager getsInstance(Context ctx) {
        context = ctx;
        if (sInstance == null) {
            synchronized (PreferenceManager.class) {
                if (sInstance == null) {
                    sInstance = new PreferenceManager(ctx);
                }
            }
        }
        return sInstance;
    }

    private PreferenceManager(Context ctx) {
        sharedPrefs = ctx.getSharedPreferences(PreferenceKeys.SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
    }

    public void setFirstLaunch(boolean isFirstLaunch) {
        editor.putBoolean(PreferenceKeys.IS_FIRST_LAUNCH, isFirstLaunch).commit();
    }

    public boolean isFirstLaunch() {
        return sharedPrefs.getBoolean(PreferenceKeys.IS_FIRST_LAUNCH, true);
    }

}
