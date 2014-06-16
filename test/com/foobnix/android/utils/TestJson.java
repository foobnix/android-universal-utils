package com.foobnix.android.utils;

import java.util.Date;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

public class TestJson extends TestCase {
    private String stringName;
    
    private Boolean boolean1;
    private boolean boolean2;
                                    
    private int int1;
    private Integer int2;
                                    
    private double double1;
    private Double double2;
                                    
    private float float1;
    private Double float2;
                                    
    private long long1;
    private Long long2;
    private Date date;

    
    public void test1() throws JSONException {
        JSONObject ojb = new JSONObject();

        stringName = ojb.getString("stringName");
        boolean1 = ojb.getBoolean("boolean1");
        boolean2 = ojb.getBoolean("boolean2");
        int1 = ojb.optInt("int1");
        int2 = ojb.optInt("int2");
        double1 = ojb.getDouble("double1");
        double2 = ojb.optDouble("double2");
        float1 = (float) ojb.getDouble("float1");
        float2 = ojb.optDouble("float2");
        long1 = ojb.getLong("long1");
        long2 = ojb.optLong("long2");

        ojb.put("stringName", stringName);
        ojb.put("boolean1", boolean1);
        ojb.put("boolean2", boolean2);
        ojb.put("int1", int1);
        ojb.put("int2", int2);
        ojb.put("double1", double1);
        ojb.put("double2", double2);
        ojb.put("float1", float1);
        ojb.put("float2", float2);
        ojb.put("long1", long1);
        ojb.put("long2", long2);
      

    }

}
