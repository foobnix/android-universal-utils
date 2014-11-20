package com.foobnix.android.utils;

public class Int {
    private int value;

    public Int(int value) {
        this.value = value;
    }

    public void plus(int value) {
        this.value += value;
    }

    public void minus(int value) {
        this.value -= value;
    }

    public void multiply(int value) {
        this.value *= value;
    }

    public void divide(int value) {
        this.value *= value;
    }

    public int getValue() {
        return value;
    }
}
