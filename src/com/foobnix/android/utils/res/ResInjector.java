package com.foobnix.android.utils.res;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.foobnix.android.utils.Apps;
import com.foobnix.android.utils.LOG;
import com.foobnix.android.utils.ModelFragment;
import com.foobnix.android.utils.ResultResponse;

@SuppressLint("NewApi")
public class ResInjector {

    private static final String ID = "id";
    private static String PACKAGE_NAME;
    private static Map<String, Integer> cache = new HashMap<String, Integer>();

    public static void inject(View view, ModelFragment<?> obj, Bundle bundle) {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = Apps.getPackageName(view.getContext());
        }
        injectInner(view, obj);
        onRestoreInstanceState(obj, bundle);
    }

    public static void inject(Activity obj) {
        new RuntimeException("Use Fragments");
    }

    private static void onRestoreInstanceState(Object f, Bundle outState) {
        if (outState == null) {
            return;
        }
        for (Field field : f.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SaveState.class)) {
                String name = field.getName();
                field.setAccessible(true);
                try {
                    if (outState.containsKey(name)) {
                        field.set(f, outState.get(name));
                    }
                } catch (Exception e) {
                    LOG.e(e);
                }
            }
        }
    }

    private static void onSaveInstanceState(Object f, Bundle outState) {
        if (outState == null) {
            return;
        }
        for (Field field : f.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SaveState.class)) {
                String name = field.getName();
                field.setAccessible(true);
                try {
                    Object value = field.get(f);
                    Class<?> type = field.getType();
                    if (type.equals(String.class)) {
                        outState.putString(name, (String) value);
                    } else if (type.equals(Integer.class)) {
                        outState.putInt(name, (Integer) value);
                    } else if (type.equals(Boolean.class)) {
                        outState.putBoolean(name, (Boolean) value);
                    } else if (type instanceof Serializable) {
                        outState.putSerializable(name, (Serializable) value);
                        // } else if (type instanceof Parcelable) {
                        // outState.putParcelable(name, (Parcelable) value);
                    } else {
                        throw new RuntimeException("Not supported type " + type + " " + field.getName());
                    }
                } catch (Exception e) {
                    LOG.e(e);
                }
            }
        }
    }

    public static void copyModelToView(Object model, View view) {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = Apps.getPackageName(view.getContext());
        }
        Field[] declaredFields = model.getClass().getDeclaredFields();
        for (final Field field : declaredFields) {
            if (field.isAnnotationPresent(ResId.class)) {
                ResId annotation = field.getAnnotation(ResId.class);
                int resID = annotation.value();
                if (resID == 0) {
                    throw new RuntimeException("Res Id not found" + field.getName());
                }
                try {
                    View findViewById = view.findViewById(resID);
                    if (findViewById instanceof Switch) {
                        field.setAccessible(true);
                        ((Switch) findViewById).setChecked(field.getBoolean(model));
                    } else if (findViewById instanceof CheckBox) {
                        field.setAccessible(true);
                        ((CheckBox) findViewById).setChecked(field.getBoolean(model));
                    } else if (findViewById instanceof EditText) {
                        field.setAccessible(true);
                        ((EditText) findViewById).setText(field.get(model).toString());
                    } else if (findViewById instanceof TextView) {
                        field.setAccessible(true);
                        ((TextView) findViewById).setText(field.get(model).toString());
                    } else {
                        throw new RuntimeException("copyModelToView View not mapped " + field.getName());
                    }

                } catch (Exception e) {
                    LOG.e(e);
                }
            }
        }
    }

    public static void copyViewToModel(View view, Object model) {
        if (PACKAGE_NAME == null) {
            PACKAGE_NAME = Apps.getPackageName(view.getContext());
        }
        Field[] declaredFields = model.getClass().getDeclaredFields();
        for (final Field field : declaredFields) {
            if (field.isAnnotationPresent(ResId.class)) {
                ResId annotation = field.getAnnotation(ResId.class);
                int resID = annotation.value();
                if (resID == 0) {
                    throw new RuntimeException("copyViewToModel View not mapped " + field.getName());
                }

                try {
                    View findViewById = view.findViewById(resID);
                    if (findViewById instanceof Switch) {
                        field.setAccessible(true);
                        field.set(model, ((Switch) findViewById).isChecked());
                    } else if (findViewById instanceof CheckBox) {
                        field.setAccessible(true);
                        field.set(model, ((CheckBox) findViewById).isChecked());
                    } else if (findViewById instanceof EditText) {
                        field.setAccessible(true);
                        field.set(model, ((EditText) findViewById).getText().toString());
                    } else if (findViewById instanceof TextView) {
                        field.setAccessible(true);
                        field.set(model, ((TextView) findViewById).getText().toString());
                    } else {
                        throw new RuntimeException("View not mapped " + field.getName());
                    }

                } catch (Exception e) {
                    LOG.e(e);
                }
            }
        }

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

    private static void injectInner(View view, final ModelFragment<?> fragment) {
        Class<? extends Object> clazz = fragment.getClass();

        fragment.setOnSaveInstanceState(new ResultResponse<Bundle>() {
            @Override
            public boolean onResultRecive(Bundle outState) {
                onSaveInstanceState(fragment, outState);
                return false;
            }
        });

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ResId.class)) {
                ResId annotation = field.getAnnotation(ResId.class);
                int resID = annotation.value();
                if (resID == 0) {
                    resID = findResID(field.getName(), view.getContext());
                }
                View findViewById = view.findViewById(resID);
                field.setAccessible(true);
                try {
                    field.set(fragment, findViewById);
                } catch (Exception e) {
                    LOG.e(e);
                }
            }
            if (field.isAnnotationPresent(ExtraArgument.class)) {
                ExtraArgument annotation = field.getAnnotation(ExtraArgument.class);
                String key = annotation.value();
                applyExtraArgumentAnnotation(fragment, field, key);
            }

            if (field.isAnnotationPresent(ExtraArgument_1.class)) {
                ExtraArgument_1 annotation = field.getAnnotation(ExtraArgument_1.class);
                String key = annotation.value();
                applyExtraArgumentAnnotation(fragment, field, key);
            }

            if (field.isAnnotationPresent(ExtraArgument_2.class)) {
                ExtraArgument_2 annotation = field.getAnnotation(ExtraArgument_2.class);
                String key = annotation.value();
                applyExtraArgumentAnnotation(fragment, field, key);
            }

            if (field.isAnnotationPresent(ExtraArgument_3.class)) {
                ExtraArgument_3 annotation = field.getAnnotation(ExtraArgument_3.class);
                String key = annotation.value();
                applyExtraArgumentAnnotation(fragment, field, key);
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
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (parameterTypes.length == 1) {
                                method.invoke(fragment, v);
                            } else {
                                method.invoke(fragment);
                            }
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
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (parameterTypes.length == 1) {
                                method.invoke(fragment, v);
                            } else {
                                method.invoke(fragment);
                            }
                        } catch (Exception e) {
                            LOG.e(e);
                        }
                        return true;
                    }
                });
            }

            if (method.isAnnotationPresent(TickTimer.class)) {
                final TickTimer annotation = method.getAnnotation(TickTimer.class);

                final Handler handler = fragment.getHandler();

                final Runnable run = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            method.invoke(fragment);
                        } catch (Exception e) {
                            LOG.e(e);
                        }
                        handler.postDelayed(this, annotation.value());
                    }
                };

                fragment.setOnPauseLintener(new Runnable() {

                    @Override
                    public void run() {
                        handler.removeCallbacksAndMessages(null);
                    }
                });
                fragment.setOnResumeLintener(new Runnable() {

                    @Override
                    public void run() {
                        handler.post(run);
                    }
                });
            }
        }

    }

    private static void applyExtraArgumentAnnotation(final ModelFragment<?> fragment, Field field, String key) {
        Bundle arguments = fragment.getArguments();
        if (arguments != null && arguments.containsKey(key)) {
            if (!(field.getType() instanceof Serializable)) {
                throw new RuntimeException("Field should be Serializable " + field.getName());
            }
            Object value = arguments.getSerializable(key);
            field.setAccessible(true);
            try {
                field.set(fragment, value);
            } catch (Exception e) {
                LOG.e(e);
            }
        }
    }

}
