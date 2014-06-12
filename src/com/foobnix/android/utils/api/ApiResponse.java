package com.foobnix.android.utils.api;

public class ApiResponse<T> {
    private int code;
    private String response;
    private T item;

    public ApiResponse(int code, String response, Class<T> clazz) {
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

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

}
