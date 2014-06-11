package com.foobnix.android.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HTTP {

    public static DefaultHttpClient buildMultiThread() {
        HttpParams params = new BasicHttpParams();
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
        return new DefaultHttpClient(cm, params);
    }

    public static String toString(String url) throws ClientProtocolException, IOException {
        HttpGet http = new HttpGet(url);
        HttpResponse execute = buildMultiThread().execute(http);
        return EntityUtils.toString(execute.getEntity());
    }

}
