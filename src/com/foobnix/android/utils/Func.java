package com.foobnix.android.utils;

import java.util.List;

public class Func {

    public static <T> void forAll(ResultResponse<T> result, T... views) {
        for (T view : views) {
            result.onResultRecive(view);
        }
    }

    public static <T> void forAll(ResultResponse<T> result, List<T> views) {
        for (T view : views) {
            result.onResultRecive(view);
        }
    }

    public static <T> void forAll(ResultIndexResponse<T> result, T... views) {
        for (int i = 0; i < views.length; i++) {
            result.onResultRecive(i, views[i]);
        }
    }

    public static <T> void forAll(ResultIndexResponse<T> result, List<T> views) {
        for (int i = 0; i < views.size(); i++) {
            result.onResultRecive(i, views.get(i));
        }
    }

}
