package com.foobnix.android.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;

public abstract class ModelFragment<T extends Serializable> extends Fragment {
    public static final String EXTRA_FRAGMENT_MODEL = "model";

    public static final String EXTRA_ARGUMENT_1 = "EXTRA_ARGIMENT_1";
    public static final String EXTRA_ARGUMENT_2 = "EXTRA_ARGIMENT_2";
    public static final String EXTRA_ARGUMENT_3 = "EXTRA_ARGIMENT_3";

    private Handler handler;

    private Runnable onPauseLintener;
    private Runnable onResumeLintener;
    private ResultResponse<Bundle> onSaveInstanceState;
    protected T model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        prepareModelFromArgumentOrBundle(savedInstanceState);
    }

    private void prepareModelFromArgumentOrBundle(Bundle savedInstanceState) {
        if (getArguments() != null && getArguments().containsKey(EXTRA_FRAGMENT_MODEL)) {
            model = (T) getArguments().getSerializable(EXTRA_FRAGMENT_MODEL);
        }
        if (model == null && savedInstanceState != null) {
            model = (T) savedInstanceState.getSerializable(EXTRA_FRAGMENT_MODEL);
        }
        if (model == null) {
            try {
                ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
                Class<?> type = (Class<?>) pt.getActualTypeArguments()[0];
                LOG.d("Model Fragment class name", type.getName());
                model = (T) type.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public abstract void populateModel();

    public abstract void saveModel();

    @Override
    public void onPause() {
        super.onPause();
        if (onPauseLintener != null) {
            onPauseLintener.run();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onResumeLintener != null) {
            onResumeLintener.run();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveModel();
        outState.putSerializable(EXTRA_FRAGMENT_MODEL, model);
        if (outState != null && onSaveInstanceState != null) {
            onSaveInstanceState.onResultRecive(outState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setOnPauseLintener(Runnable onPauseLintener) {
        this.onPauseLintener = onPauseLintener;
    }

    public void setOnResumeLintener(Runnable onResumeLintener) {
        this.onResumeLintener = onResumeLintener;
    }

    public void setOnSaveInstanceState(ResultResponse<Bundle> onSaveInstanceState) {
        this.onSaveInstanceState = onSaveInstanceState;
    }

}
