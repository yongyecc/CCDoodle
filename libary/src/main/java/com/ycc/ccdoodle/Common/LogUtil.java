package com.ycc.ccdoodle.Common;

import android.util.Log;

public class LogUtil {
    private static final String LOG_PREFIX = "CCDoodle: ";

    private static final boolean DEBUG = true;

    /**
     * Log.v .
     *
     * @param tag Log TAG .
     * @param msg Log msg .
     */
    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(LOG_PREFIX + tag, msg);
        }
    }

    /**
     * Log.d .
     *
     * @param tag Log TAG .
     * @param msg Log msg .
     */
    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(LOG_PREFIX + tag, msg);
        }
    }

    /**
     * Log.i .
     *
     * @param tag Log TAG .
     * @param msg Log msg .
     */
    public static void i(String tag, String msg) {
        Log.i(LOG_PREFIX + tag, msg);
    }

    /**
     * Log.w .
     *
     * @param tag Log TAG .
     * @param msg Log msg .
     */
    public static void w(String tag, String msg) {
        Log.w(LOG_PREFIX + tag, msg);
    }

    /**
     * Log.e .
     *
     * @param tag Log TAG .
     * @param msg Log msg .
     */
    public static void e(String tag, String msg) {
        Log.e(LOG_PREFIX + tag, msg);
    }

}
