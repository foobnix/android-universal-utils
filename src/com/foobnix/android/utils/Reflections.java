package com.foobnix.android.utils;

import java.lang.reflect.Field;

public class Reflections {

    public static String getFieldNameForValue(String prefix, Class<?> clazz, int value) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            try {
                if (f.getType().equals(int.class) && f.getInt(null) == value) {
                    return f.getName();
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";

    }

}
