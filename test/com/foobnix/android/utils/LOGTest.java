package com.foobnix.android.utils;

import java.util.Date;

import android.test.AndroidTestCase;

public class LOGTest extends AndroidTestCase {

    public void testLog() {
        LOG.TAG = "DEBUG";
        LOG.DELIMITER = "|";

        LOG.d("Hello", "Log", 5, Long.valueOf(99l), new Date());
    }

}
