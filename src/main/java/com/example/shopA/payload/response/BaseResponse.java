package com.example.shopA.payload.response;

public class BaseResponse<T> {
    private T data;
    private String code;
    private String message;

    public BaseResponse(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
}
