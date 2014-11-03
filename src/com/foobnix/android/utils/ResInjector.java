package com.foobnix.android.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.view.View;

public class ResInjector {

    public static void inject(View view, android.support.v4.app.Fragment obj) {
        injectInner(view, obj);
    }

    public static void inject(View view, android.app.Fragment obj) {
        injectInner(view, obj);
    }

    public static void inject(View view, Activity obj) {
        injectInner(view, obj);
    }

    private static void injectInner(View view, Object obj) {
        Class<? extends Object> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ResId.class)) {
                int resID = view.getResources().getIdentifier(field.getName(), "id", Apps.getPackageName(view.getContext()));
                if (resID == 0) {
                    throw new RuntimeException(String.format("View not found %s::%s", clazz.getName(), field.getName()));
                }
                View findViewById = view.findViewById(resID);
                field.setAccessible(true);
                try {
                    field.set(obj, findViewById);
                } catch (IllegalAccessException e) {
                    LOG.e(e);
                }
            }
        }
    }

    public static void reset(Object obj) {
        Class<? extends Object> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ResId.class)) {
                field.setAccessible(true);
                try {
                    field.set(obj, null);
                } catch (IllegalAccessException e) {
                    LOG.e(e);
                }
            }
        }
    }

}
