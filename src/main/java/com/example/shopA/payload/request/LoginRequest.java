package com.example.shopA.payload.request;

import org.springframework.lang.NonNull;

public class LoginRequest {
    @NonNull
    private String mailAddress;
    @NonNull
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
