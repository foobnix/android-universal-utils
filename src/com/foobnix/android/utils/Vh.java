package com.foobnix.android.utils;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * View holder
 * 
 * @author ivan
 * 
 */
public class Vh {

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    public static TextView text(View view, int id) {
        return (TextView) get(view, id);
    }

    public static TextView text(View view, int id, String text) {
        TextView textView = (TextView) get(view, id);
        textView.setText(text);
        return textView;
    }

    public static ImageView image(View view, int id) {
        return (ImageView) get(view, id);
    }
}