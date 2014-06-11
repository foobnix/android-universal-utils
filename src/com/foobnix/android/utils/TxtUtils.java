package com.foobnix.android.utils;


public class TxtUtils {

    public static String nullToEmpty(String txt) {
        if (txt == null) {
            return "";
        }
        return txt;
    }

    public static boolean isEmpty(String txt) {
        return txt == null || txt.trim().length() == 0;
    }

    public static boolean isNotEmpty(String txt) {
        return !isEmpty(txt);
    }

    public static String join(String delim, Object... items) {
        StringBuilder sb = new StringBuilder();
        for (Object it : items) {
            sb.append(it);
            sb.append(delim);
        }
        return sb.toString().trim();
    }

    public static String join(Object... items) {
        return join(" ", items);
    }

}
