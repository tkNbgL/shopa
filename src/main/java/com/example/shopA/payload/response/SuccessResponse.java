package com.example.shopA.payload.response;

import java.util.Date;

public class SuccessResponse <T>{
    private String statusCode;
    private T data;
    private String message;
    private Date transactionDate;

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public SuccessResponse(SuccessResponseBuilder<T> successResponseBuilder) {
        this.statusCode = successResponseBuilder.statusCode;
        this.data = successResponseBuilder.data;
        this.message = successResponseBuilder.message;
        this.transactionDate = successResponseBuilder.transactionDate;
    }

    public static class SuccessResponseBuilder <T> {
        private String statusCode;
        private T data;
        private String message;
        private Date transactionDate;

        public SuccessResponseBuilder(T data, String message) {
            this.data = data;
            this.message = message;
        }

        public SuccessResponseBuilder setStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public SuccessResponseBuilder setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public SuccessResponse<T> build() {
            return new SuccessResponse<T>(this);
        }
    }
}
