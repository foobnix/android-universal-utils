package com.foobnix.android.utils;

import android.util.Log;

public class LOG {
    public static boolean isEnable = true;
    public static String TAG = "DEBUG";
    public static String DELIMITHER = "|";

    public static void printlog(String statement) {
        if (isEnable) {
            Log.d(TAG, statement);
        }
    }

    public static void d(Object... statement) {
        if (isEnable) {
            Log.d(TAG, asString(statement));
        }
    }

    public static void e(Throwable e, Object... statement) {
        if (isEnable) {
            Log.e(TAG, asString(statement), e);
        }
    }

    private static String asString(Object... statements) {
        StringBuilder b = new StringBuilder();
        for (Object str : statements) {
            b.append(str);
            b.append(DELIMITHER);
        }
        String string = b.toString();
        return string;
    }

}
