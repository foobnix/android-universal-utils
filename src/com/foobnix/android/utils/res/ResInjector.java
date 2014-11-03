package com.foobnix.android.utils.res;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import com.foobnix.android.utils.Apps;
import com.foobnix.android.utils.LOG;

public class ResInjector {

    public static void inject(View view, android.support.v4.app.Fragment obj) {
        injectInner(view, obj);
    }

    public static void inject(View view, android.app.Fragment obj) {
        injectInner(view, obj);
    }

    public static void inject(Activity obj) {
        new RuntimeException("Use Fragments");
    }

    private static void injectInner(View view, final Object obj) {
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
                } catch (Exception e) {
                    LOG.e(e);
                }
            }
        }

        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ResIdOnClick.class)) {
                ResIdOnClick annotation = method.getAnnotation(ResIdOnClick.class);
                int resID = annotation.value();
                if (resID == 0) {
                    resID = view.getResources().getIdentifier(method.getName(), "id", Apps.getPackageName(view.getContext()));
                    if (resID == 0) {
                        throw new RuntimeException(String.format("View not found %s::%s", clazz.getName(), method.getName()));
                    }
                }

                view.findViewById(resID).setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(obj);
                        } catch (Exception e) {
                            LOG.e(e);
                        }
                    }
                });

            }
        }

        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ResIdOnLongClick.class)) {
                ResIdOnLongClick annotation = method.getAnnotation(ResIdOnLongClick.class);
                int resID = annotation.value();
                if (resID == 0) {
                    resID = view.getResources().getIdentifier(method.getName(), "id", Apps.getPackageName(view.getContext()));
                    if (resID == 0) {
                        throw new RuntimeException(String.format("View not found %s::%s", clazz.getName(), method.getName()));
                    }
                }

                view.findViewById(resID).setOnLongClickListener(new OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        try {
                            method.invoke(obj);
                        } catch (Exception e) {
                            LOG.e(e);
                        }
                        return true;
                    }
                });

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
