package com.foobnix.android.utils;

import java.io.Serializable;

import android.os.Bundle;
import android.os.Parcelable;

public class Bundles {
    private final Bundle bundle = new Bundle();

    public static Bundle withParsable(final String key, final Parcelable value) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, value);
        return bundle;
    }

    public static Bundle withSerializable(final String key, final Serializable value) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, value);
        return bundle;
    }

    public static Bundle withInt(final String key, final int value) {
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        return bundle;
    }

    public static Bundle withString(final String key, final String value) {
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        return bundle;
    }

    public Bundles add(final String key, final int value) {
        bundle.putInt(key, value);
        return this;
    }

    public Bundles add(final String key, final String value) {
        bundle.putString(key, value);
        return this;
    }

    public Bundles add(final String key, final boolean value) {
        bundle.putBoolean(key, value);
        return this;
    }

    public Bundles add(final String key, final Serializable value) {
        bundle.putSerializable(key, value);
        return this;
    }

    public Bundles add(final String key, final Parcelable value) {
        bundle.putParcelable(key, value);
        return this;
    }

    public Bundle build() {
        return bundle;
    }

}
