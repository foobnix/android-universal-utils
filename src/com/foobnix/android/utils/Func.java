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

}
