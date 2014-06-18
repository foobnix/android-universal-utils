package com.foobnix.android.utils.api;

public class HttpsResponse {
    protected int code;
    protected String response;

    public HttpsResponse(int code, String response) {
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
