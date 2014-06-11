package com.foobnix.android.utils;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Views {

    public static View click(View v, int resId, OnClickListener onClick) {
        View findViewById = v.findViewById(resId);
        if (onClick != null) {
            findViewById.setOnClickListener(onClick);
        }
        return findViewById;
    }

    public static View click(Activity a, int resId, OnClickListener onClick) {
        View findViewById = a.findViewById(resId);
        if (onClick != null) {
            findViewById.setOnClickListener(onClick);
        }
        return findViewById;
    }

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

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
