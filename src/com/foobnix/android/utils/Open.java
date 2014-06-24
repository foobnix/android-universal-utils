package com.foobnix.android.utils;

import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Open {

    public static boolean browser(Context c, String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            c.startActivity(browserIntent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean playMarket(Context c, String packageName) {
        return browser(c, "market://details?id=" + packageName);
    }

    public static boolean call(Context c, String number) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            c.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void map(Context c, double lat, double lon) {
        StringBuilder res = new StringBuilder();
        res.append(String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(res.toString()));
        c.startActivity(intent);
    }

    public static void map(Context c, String label) {
        StringBuilder res = new StringBuilder();
        res.append(String.format(Locale.ENGLISH, "geo:0,0&q=%s", label));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(res.toString()));
        c.startActivity(intent);
    }

}
