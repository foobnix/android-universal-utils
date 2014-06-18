package com.foobnix.android.utils;

import android.test.AndroidTestCase;

import com.foobnix.android.utils.api.HttpType;
import com.foobnix.android.utils.api.Https;
import com.foobnix.android.utils.api.HttpsResponse;

public class HttpTest extends AndroidTestCase {

    public void test_GET() {
        HttpsResponse call = Https.get().call(HttpType.GET, "http://search.issuu.com/api/2_0/document?q=jamie", null);
        assertEquals(call.getCode(), 200);
        assertNotNull(call.getResponse());
    }

}
