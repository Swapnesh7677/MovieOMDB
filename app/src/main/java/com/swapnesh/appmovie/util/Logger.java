package com.swapnesh.appmovie.util;

import android.util.Log;

import com.swapnesh.appmovie.BuildConfig;


public class Logger {
    public static void d(String tag, String value) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, value);
        }
    }

    public static void i(String tag, String value) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, value);
        }
    }

    public static void logApi(String tag, String api_name, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, api_name + " => " + msg);
        }
    }
}
