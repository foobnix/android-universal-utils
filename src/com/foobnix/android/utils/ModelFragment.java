package com.foobnix.android.utils;

import java.io.Serializable;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

public abstract class ModelFragment<T extends Serializable> extends Fragment {
    public static final String EXTRA_FRAGMENT_MODEL = "model";
    protected T model;
    protected Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }

    public void prepareModelFromArgumentOrBundle(Bundle savedInstanceState, Class<T> clazz) {
        if (getArguments() != null && getArguments().containsKey(EXTRA_FRAGMENT_MODEL)) {
            model = (T) getArguments().getSerializable(EXTRA_FRAGMENT_MODEL);
        }
        if (model == null && savedInstanceState != null) {
            model = (T) savedInstanceState.getSerializable(EXTRA_FRAGMENT_MODEL);
        }
        if (model == null) {
            try {
                model = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
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
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
