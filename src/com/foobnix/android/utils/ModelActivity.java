package com.foobnix.android.utils;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public abstract class ModelActivity<T extends Serializable> extends Activity {
    public static final String EXTRA_FRAGMENT_MODEL = "model";
    protected T model;
    protected Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    public void prepareModelFromArgumentOrBundle(Bundle savedInstanceState, Class<T> clazz) {
        if (getIntent() != null && getIntent().hasExtra(EXTRA_FRAGMENT_MODEL)) {
            model = (T) getIntent().getSerializableExtra(EXTRA_FRAGMENT_MODEL);
        }
        if (model == null && savedInstanceState != null) {
            model = (T) savedInstanceState.getSerializable(EXTRA_FRAGMENT_MODEL);
        }
        if (model == null) {
            try {
                model = clazz.newInstance();
            } catch (Exception e) {
                LOG.e(e);
            }
        }

    }

    public abstract void populateModel();

    public abstract void saveModel();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveModel();
        outState.putSerializable(EXTRA_FRAGMENT_MODEL, model);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
