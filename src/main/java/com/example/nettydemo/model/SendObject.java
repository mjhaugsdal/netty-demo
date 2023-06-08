package com.example.nettydemo.model;

import java.nio.file.Path;

public class SendObject {


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    byte[] data;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    String header;

    public SendObject(byte[] data, String header) {
        this.data = data;
        this.header = header;
    }

}
