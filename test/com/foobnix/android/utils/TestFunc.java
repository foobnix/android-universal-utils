package com.foobnix.android.utils;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.TestCase;

public class TestFunc extends TestCase {

    public void testFindIndex(){
        assertEquals(2, Collections.binarySearch(Arrays.asList(12, 13, 14, 15), 14));
        assertEquals(-1, Collections.binarySearch(Arrays.asList(12, 13, 14, 15), 141));
    }

}
