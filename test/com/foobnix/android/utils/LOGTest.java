package com.foobnix.android.utils;

import java.util.Date;

import android.R;
import android.test.AndroidTestCase;
import android.widget.TextView;

public class LOGTest extends AndroidTestCase {

    class Token {

    }
    public void testLog() {
        LOG.TAG = "DEBUG";
        LOG.DELIMITER = "|";

        LOG.d("Hello", "Log", 5, Long.valueOf(99l), new Date());

        TextView name = Views.text(null, R.id.text1, "My name");

        ResultResponse<Token> token = new ResultResponse<Token>() {

            @Override
            public void onResultRecive(Token result) {

            }
        };
    }

}
