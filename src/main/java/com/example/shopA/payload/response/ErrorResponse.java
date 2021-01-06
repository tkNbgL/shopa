package com.example.shopA.payload.response;

import java.util.Date;

public class ErrorResponse {
    private String statusCode;
    private String message;
    private Date transactionDate;

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public ErrorResponse(ErrorResponseBuilder errorResponseBuilder) {
        this.statusCode = errorResponseBuilder.statusCode;
        this.message = errorResponseBuilder.message;
        this.transactionDate = errorResponseBuilder.transactionDate;
    }

    public static class ErrorResponseBuilder {
        private String statusCode;
        private String message;
        private Date transactionDate;

        public ErrorResponseBuilder(String statusCode) {
            this.statusCode = statusCode;
        }

        public ErrorResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

}
