package com.foobnix.android.utils;

import android.os.Bundle;

public class Bundles {
    private final Bundle bundle = new Bundle();

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

    public Bundles add(String key, int value) {
        bundle.putInt(key, value);
        return this;
    }

    public Bundles add(String key, String value) {
        bundle.putString(key, value);
        return this;
    }

    public Bundles add(String key, boolean value) {
        bundle.putBoolean(key, value);
        return this;
    }

    public Bundle build() {
        return bundle;
    }

}
