package com.foobnix.android.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONSerializer {

    private static final String KEY_DATE = "@date";
    private static final String KEY_MAP = "@map";
    /**
     * 
     */
    private static final List SIMPLE_TYPES = Arrays.asList(int.class, float.class, double.class, boolean.class, long.class, String.class, Integer.class, Boolean.class, Long.class,
            Float.class, Double.class);

    public static boolean isNaN(Object o) {
        if (o instanceof Float) {
            return ((Float) o).isNaN();
        }
        if (o instanceof Double) {
            return ((Double) o).isNaN();
        }
        return false;
    }

    public static <T> JSONObject toJSON(final T object) throws JSONException, IllegalArgumentException, IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }

            String name = field.getName();
            field.setAccessible(true);
            Object value = field.get(object);

            if (value != null) {
                if (SIMPLE_TYPES.contains(field.getType())) {
                    if (isNaN(value)) {
                        value = 0;
                    }
                    jsonObject.put(name, value);
                } else if (Date.class.equals(field.getType())) {
                    Date date = (Date) value;
                    jsonObject.put(name, KEY_DATE + date.getTime());
                } else if (List.class.equals(field.getType())) {
                    List<?> list = (List<?>) value;

                    if (list.isEmpty()) {
                        continue;
                    }

                    JSONArray array = new JSONArray();
                    for (Object item : list) {
                        if (SIMPLE_TYPES.contains(item.getClass())) {
                            if (isNaN(item)) {
                                item = 0;
                            }
                            array.put(item);
                        } else {
                            array.put(toJSON(item));
                        }
                    }
                    jsonObject.put(name, array);
                } else if (Map.class.equals(field.getType())) {
                    Map<?, ?> map = (Map<?, ?>) value;

                    if (map.isEmpty()) {
                        continue;
                    }
                    JSONArray root = new JSONArray();

                    for (Object key : map.keySet()) {
                        JSONObject child = new JSONObject();
                        Object mapValue = map.get(key);
                        child.put("k", key);
                        child.put("v", mapValue);
                        root.put(child);
                    }
                    jsonObject.put(name, root);
                } else if (field.getType().isEnum()) {
                    Enum en = (Enum) value;
                    jsonObject.put(name, en.name());
                } else {
                    jsonObject.put(name, toJSON(value));
                }
            }

        }
        return jsonObject;
    }

    public static <T> void fromJSON(JSONObject jsonObject, T result) throws JSONException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        Field[] declaredFields = result.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }

            String name = field.getName();
            field.setAccessible(true);
            Object value = jsonObject.opt(name);
            LOG.d(field.getName(), field.getType(), value);
            if (value == null) {
                continue;
            }
            String valueAsString = value.toString();
            if (valueAsString.startsWith(KEY_DATE)) {
                valueAsString = valueAsString.replace(KEY_DATE, "");
                field.set(result, new Date(Long.parseLong(valueAsString)));
            } else if (SIMPLE_TYPES.contains(field.getType())) {
                field.set(result, value);
            } else if (List.class.equals(field.getType())) {
                JSONArray array = (JSONArray) value;
                if (array == null || array.length() == 0) {
                    continue;
                }
                ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                Class<?> G = (Class<?>) stringListType.getActualTypeArguments()[0];

                List listResult = new ArrayList();

                for (int i = 0; i < array.length(); i++) {
                    Object object = array.get(i);
                    if (SIMPLE_TYPES.contains(object.getClass())) {
                        listResult.add(object);
                    } else {
                        JSONObject jsonItem = array.getJSONObject(i);
                        Object itemList = G.newInstance();
                        fromJSON(jsonItem, itemList);

                        listResult.add(itemList);
                    }
                }

                field.set(result, listResult);
            } else if (field.getType().isEnum()) {
                field.set(result, Enum.valueOf((Class<Enum>) field.getType(), (String) value));
            } else if (Map.class.equals(field.getType())) {
                Map<Object, Object> map = new HashMap<Object, Object>();
                JSONArray array = (JSONArray) value;
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    map.put(obj.get("k"), obj.get("v"));
                }
                field.set(result, map);
            } else {
                Object res = field.getType().newInstance();
                fromJSON((JSONObject) value, res);
                field.set(result, res);
            }
        }

    }

}
