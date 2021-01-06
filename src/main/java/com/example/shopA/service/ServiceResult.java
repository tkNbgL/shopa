package com.example.shopA.service;

public class ServiceResult <T> {
    private int code;
    private String message;
    private T result;

    public ServiceResult(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ServiceResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }
}
