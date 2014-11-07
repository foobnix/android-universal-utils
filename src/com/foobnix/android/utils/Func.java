package com.foobnix.android.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Pair;

public class Func {

    public static <T> List<T> filter(ResultResponse<T> result, List<T> input) {
        List<T> res = new ArrayList<T>();
        if (input == null || res == null) {
            return res;
        }
        for (T item : input) {
            if (result.onResultRecive(item)) {
                res.add(item);
            }
        }
        return res;
    }

    public static <T> void remove(ResultResponse<T> result, List<T> input) {
        if (input == null || result == null) {
            return;
        }
        Iterator<T> iterator = input.iterator();
        while(true){
            if (result.onResultRecive(iterator.next())) {
                iterator.remove();
            }
        }
    }

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

    public static <T> T find(ResultResponse<T> onFindCriteria, List<T> views) {
        for (T view : views) {
            if (onFindCriteria.onResultRecive(view)) {
                return view;
            }
        }
        return null;
    }

    public static <T> Pair<T, Integer> findIndex(ResultResponse<T> onFindCriteria, List<T> views) {
        for (int i = 0; i < views.size(); i++) {
            T view = views.get(i);
            if (onFindCriteria.onResultRecive(view)) {
                return new Pair<T, Integer>(view, i);
            }
        }
        return new Pair<T, Integer>(null, -1);
    }

}
