package com.foobnix.android.utils;

import android.test.AndroidTestCase;

public class TestApps extends AndroidTestCase {

    public void testIsInstaller() {
        assertFalse(Apps.isPackageInstalled("com.user.demo", getContext()));
        assertTrue(Apps.isPackageInstalled("com.alteso.partsbee", getContext()));
    }

}
