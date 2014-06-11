package com.foobnix.android.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.test.AndroidTestCase;

public class JSONSerializerTest extends AndroidTestCase {

    enum DemoEnum {
        ONE, TWO
    }
    class Demo {
        private int a;

        private Integer a1;
        private Integer a2;

        private String b;
        private String b1;

        private Date date;
        private List<String> list1;
        private List<Integer> list2;
        private List<Integer> list3;
        private List<Integer> list4;

        private DemoEnum enum1;
        private DemoEnum enum2;

        private Map<String, Integer> map1;
        private Map<String, Integer> map2;
        private Map<String, Integer> map3;
    }

    public void test1() throws Exception {
        Demo in = new Demo();
        in.a = 123;
        in.a1 = 123;
        in.a2 = null;
        in.list1 = Arrays.asList("a", "b");
        in.list2 = Arrays.asList(1, 2);
        in.list3 = null;
        in.list4 = new ArrayList<Integer>();

        in.b = "sdf";
        in.b1 = null;

        in.date = new Date();

        in.enum1 = DemoEnum.ONE;
        in.enum2 = null;

        in.map1 = new HashMap<>();
        in.map2 = null;
        in.map3 = new HashMap<>();
        in.map3.put("key1", 1);
        in.map3.put("key2", 2);
        in.map3.put("key3", 3);

        JSONObject json = JSONSerializer.toJSON(in);
        LOG.d(json);

        Demo out = new Demo();
        JSONSerializer.fromJSON(json, out);

        assertEquals(in.a, out.a);
        assertEquals(in.a1, out.a1);
        assertEquals(in.a2, out.a2);

        assertEquals(in.b, out.b);
        assertEquals(in.b1, out.b1);

        assertEquals(in.date, out.date);

        assertEquals(in.list1, out.list1);
        assertEquals(in.list2, out.list2);
        assertEquals(in.list3, out.list3);

        assertEquals(in.enum1, out.enum1);
        assertEquals(in.enum2, out.enum2);

        assertEquals(null, out.map1);
        assertEquals(in.map2, out.map2);
        assertEquals(in.map3, out.map3);
        assertEquals(in.map3.get("key1"), out.map3.get("key1"));

    }

}
