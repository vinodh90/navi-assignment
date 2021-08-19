package com.dweepdroid.github.common;

import android.util.Log;

public class Logger {

    private static final int LOG_LEVEL = Log.VERBOSE;

    private static final String TAG_PREFIX = "NaviGithub ";

    public static synchronized int v(String tag, String msg) {
        msg = "\n" + msg;
        return LOG_LEVEL <= android.util.Log.VERBOSE ? android.util.Log.v(TAG_PREFIX + tag, msg) : 0;
    }

    public static synchronized int d(String tag, String msg) {
        msg = "\n" + msg;

        return LOG_LEVEL <= android.util.Log.DEBUG ? android.util.Log.d(TAG_PREFIX + tag, msg) : 0;
    }

    public static synchronized int i(String tag, String msg) {
        msg = "\n" + msg;
        return LOG_LEVEL <= android.util.Log.INFO ? android.util.Log.i(TAG_PREFIX + tag, msg) : 0;
    }

    public static synchronized int w(String tag, String msg) {
        msg = "\n" + msg;
        return LOG_LEVEL <= android.util.Log.WARN ? android.util.Log.w(TAG_PREFIX + tag, msg) : 0;
    }

    public static synchronized int w(String tag, Throwable tr) {
        return LOG_LEVEL <= android.util.Log.WARN ? android.util.Log.w(TAG_PREFIX + tag, tr) : 0;
    }

    public static synchronized int e(String tag, String msg) {
        msg = "\n" + msg;
        return LOG_LEVEL <= android.util.Log.ERROR ? android.util.Log.e(TAG_PREFIX + tag, msg) : 0;
    }

    public static void log(String msg) {
        Log.d("Log",msg);
    }

}
