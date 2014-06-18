package com.foobnix.android.utils;

import android.annotation.SuppressLint;
import android.test.AndroidTestCase;
import android.view.DragEvent;
import android.view.MotionEvent;

@SuppressLint("NewApi")
public class TestReflection extends AndroidTestCase {

    public void testReflection() {
        assertEquals("ACTION_DRAG_ENDED", Reflections.getFieldNameForValue("ACTION_", DragEvent.class, 4));
        assertEquals("", Reflections.getFieldNameForValue("ACTION_", DragEvent.class, 99));

        assertEquals("ACTION_MOVE", Reflections.getFieldNameForValue("ACTION_", MotionEvent.class, 2));
    }

}
