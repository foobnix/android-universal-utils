package com.foobnix.android.utils;

import android.view.View;
import android.widget.TextView;

public class Views {

    public static TextView text(View view, int resId, String text) {
        TextView textView = (TextView) view.findViewById(resId);
        textView.setText(text);
        return textView;
    }

    public static TextView textIfFind(View view, int resId, String text) {
        TextView textView = (TextView) view.findViewById(resId);
        if (textView != null) {
            textView.setText(text);
        }
        return textView;
    }

}
