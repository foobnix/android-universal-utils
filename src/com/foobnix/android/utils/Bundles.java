package com.foobnix.android.utils;

import android.os.Bundle;

public class Bundles {

    public static Bundle bInt(String key, int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        return bundle;
    }

    public static Bundle bString(String key, String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        return bundle;
    }


}
