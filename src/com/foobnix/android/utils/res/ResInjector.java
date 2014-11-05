package com.foobnix.android.utils.res;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

import com.foobnix.android.utils.Apps;
import com.foobnix.android.utils.LOG;

public class ResInjector {

    private static final String ID = "id";
    private static String PACKAGE_NAME;
    private static Map<String, Integer> cache = new HashMap<String, Integer>();

    public static void inject(View view, android.support.v4.app.Fragment obj) {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = Apps.getPackageName(view.getContext());
        }
        injectInner(view, obj);
    }

    public static void inject(View view, android.app.Fragment obj) {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = Apps.getPackageName(view.getContext());
        }
        injectInner(view, obj);
    }

    public static void inject(Activity obj) {
        new RuntimeException("Use Fragments");
    }

    public static void preCacheViews(Context c, Class<?>... fragments) {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = Apps.getPackageName(c);
        }
        for (Class<?> clazz : fragments) {
            // Class<? extends Fragment> = f.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(ResId.class)) {
                    cacheResID(field.getName(), c);
                }
            }

            for (final Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ResIdOnClick.class)) {
                    ResIdOnClick annotation = method.getAnnotation(ResIdOnClick.class);
                    if (annotation.value() == 0) {
                        cacheResID(method.getName(), c);
                    }
                }
                if (method.isAnnotationPresent(ResIdOnLongClick.class)) {
                    ResIdOnLongClick annotation = method.getAnnotation(ResIdOnLongClick.class);
                    if (annotation.value() == 0) {
                        cacheResID(method.getName(), c);
                    }
                }
            }
        }
    }

    private static void cacheResID(String name, Context c) {
        if (cache.containsKey(name)) {
            return;
        }
        int resID = c.getResources().getIdentifier(name, ID, PACKAGE_NAME);
        if (resID == 0) {
            throw new RuntimeException(String.format("View Id not found %s", name));
        }
        cache.put(name, resID);

    }

    private static int findResID(String name, Context c) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }
        int resID = c.getResources().getIdentifier(name, ID, PACKAGE_NAME);
        if (resID == 0) {
            throw new RuntimeException(String.format("View Id not found %s", name));
        }
        cache.put(name, resID);
        return resID;
    }

    private static void injectInner(View view, final Object obj) {
        Class<? extends Object> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ResId.class)) {
                int resID = findResID(field.getName(), view.getContext());
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
                    resID = findResID(method.getName(), view.getContext());
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

            if (method.isAnnotationPresent(ResIdOnLongClick.class)) {
                ResIdOnLongClick annotation = method.getAnnotation(ResIdOnLongClick.class);
                int resID = annotation.value();
                if (resID == 0) {
                    resID = findResID(method.getName(), view.getContext());
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
