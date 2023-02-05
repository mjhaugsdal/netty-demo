package com.example.nettydemo.model;

public class RequestData {
    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    private String stringValue;

    // standard getters and setters
}