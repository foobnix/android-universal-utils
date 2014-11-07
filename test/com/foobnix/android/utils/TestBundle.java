package com.foobnix.android.utils;

import android.os.Bundle;
import android.test.AndroidTestCase;

public class TestBundle extends AndroidTestCase {

    public void testBundle(){
        Bundle build = new Bundles().add("bool", true).add("int", 1).add("str", "asdf").build();
        assertEquals(true, build.getBoolean("bool"));
        assertEquals(1, build.getInt("int"));
        assertEquals("asdf", build.getString("str"));
    }

}
