package com.foobnix.android.utils.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.foobnix.android.utils.LOG;

public class Https {

    public static Https instance = new Https();
    private final DefaultHttpClient httpClient = buildMultiThread();

    public static Https get() {
        return instance;
    }

    public static DefaultHttpClient buildMultiThread() {
        HttpParams params = new BasicHttpParams();
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", PlainSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);

        HttpConnectionParams.setConnectionTimeout(params, 10 * 1000);
        HttpConnectionParams.setSoTimeout(params, 10 * 1000);

        return new DefaultHttpClient(cm, params);
    }

    public HttpsResponse call(HttpType type, String fullUrl) {
        return call(type, fullUrl, null);
    }
    public HttpsResponse call(HttpType type, String fullUrl, String body) {
        LOG.d("Request Type  ", type.name());
        LOG.d("Request Url ", fullUrl);
        LOG.d("Request Body  ", body);

        int statusCode = -1;
        String result = "";
        try {
            if (type == HttpType.GET) {
                HttpGet http = new HttpGet(fullUrl);
                jsonHeades(http);

                HttpResponse execute = httpClient.execute(http);
                statusCode = execute.getStatusLine().getStatusCode();
                result = EntityUtils.toString(execute.getEntity());

            }
            if (type == HttpType.POST) {
                HttpPost http = new HttpPost(fullUrl);
                jsonHeades(http);
                if (body != null) {
                    http.setEntity(new StringEntity(body, "UTF-8"));
                }

                HttpResponse execute = httpClient.execute(http);
                statusCode = execute.getStatusLine().getStatusCode();
                result = EntityUtils.toString(execute.getEntity());
            }
        } catch (Exception e) {
            LOG.e(e);
        }
        LOG.d("Response Code  ", statusCode);
        LOG.d("Response Result", result);
        return new HttpsResponse(statusCode, result);
    }

    private void jsonHeades(HttpRequestBase http) {
        http.setHeader("Accept", "application/json");
        http.setHeader("Content-type", "application/json");
    }

}
