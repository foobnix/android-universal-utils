package com.foobnix.android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Open {

    public static void browser(Context c, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        c.startActivity(browserIntent);
    }

    public static void playMarket(Context c, String packageName) {
        browser(c, "market://details?id=" + packageName);
    }

    public static void call(Context c, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        c.startActivity(intent);
    }

}
