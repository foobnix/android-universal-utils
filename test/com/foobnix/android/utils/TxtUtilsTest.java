package com.foobnix.android.utils;

import junit.framework.TestCase;

public class TxtUtilsTest extends TestCase {

    public void test_nullNullToEmpty() {
        assertEquals("", TxtUtils.nullNullToEmpty(" "));
        assertEquals("", TxtUtils.nullNullToEmpty(null));
        assertEquals("", TxtUtils.nullNullToEmpty("  "));
        assertEquals("", TxtUtils.nullNullToEmpty("null"));
        assertEquals("", TxtUtils.nullNullToEmpty(" null "));
    }

    public void test_format() {
        assertEquals("My name is Ivan Ivanenko", TxtUtils.format$("My name is $first $second", "Ivan", "Ivanenko"));
        assertEquals("My name is Ivan Ivanenko ", TxtUtils.format$("My name is $first $second ", "Ivan", "Ivanenko"));
        assertEquals("20 years old", TxtUtils.format$("$num years old", 20));
    }

    public void test_join() {
        assertEquals("a|b|1|2", TxtUtils.join("|", "a", "b", "1", "2"));
        assertEquals("1 2 a b", TxtUtils.join(" ", 1, 2, "a", "b"));
    }
}
