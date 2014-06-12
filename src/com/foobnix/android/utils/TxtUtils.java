package com.foobnix.android.utils;

public class TxtUtils {

    public static String nullToEmpty(String txt) {
        if (txt == null) {
            return "";
        }
        return txt.trim();
    }

    public static String nullNullToEmpty(String txt) {
        if (txt == null || txt.trim().equals("null")) {
            return "";
        }
        return txt.trim();
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
        return join(" ", items).trim();
    }

    /**
     * Replace string "My name is @firstName @lastName"
     * 
     * @param str
     * @param keys
     * @return
     */
    public static String format$(String str, Object... keys) {
        if (str == null) {
            return null;
        }
        for (Object key : keys) {
            int start = str.indexOf("$");
            int end = str.indexOf(" ", start);

            if (end == -1) {
                end = str.length();
            }

            String tag = str.substring(start, end);
            str = str.replace(tag, key.toString());
        }
        return str;
    }

}
