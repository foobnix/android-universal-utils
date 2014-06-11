package com.foobnix.android.utils;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class Views {

    public static TextView text(View view, int resId, String text) {
        TextView textView = (TextView) view.findViewById(resId);
        textView.setText(text);
        return textView;
    }

    public static TextView htmlText(View view, int resId, final String htmlText) {
        TextView textView = (TextView) view.findViewById(resId);
        if (htmlText == null) {
            textView.setText("");
        } else {
            if (htmlText.contains("<")) {
                textView.setText(Html.fromHtml(htmlText));
            } else {
                textView.setText(htmlText);
            }
        }
        return textView;
    }

    public static TextView textIfFind(View view, int resId, String text) {
        TextView textView = (TextView) view.findViewById(resId);
        if (textView != null) {
            textView.setText(text);
        }
        return textView;
    }

    public static void goneAll(View view, int... resIds) {
        for (int resId : resIds) {
            View viewById = view.findViewById(resId);
            if (viewById != null) {
                viewById.setVisibility(View.GONE);
            }
        }
    }

    public static void goneAll(Activity activty, int... resIds) {
        for (int resId : resIds) {
            View viewById = activty.findViewById(resId);
            if (viewById != null) {
                viewById.setVisibility(View.GONE);
            }
        }
    }

}
