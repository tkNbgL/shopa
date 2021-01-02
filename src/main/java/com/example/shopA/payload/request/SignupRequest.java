package com.example.shopA.payload.request;

import org.springframework.lang.NonNull;

public class SignupRequest {
    @NonNull
    private String shopname;
    @NonNull
    private String mail;
    @NonNull
    private String password;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(@NonNull String shopname) {
        this.shopname = shopname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(@NonNull String email) {
        this.mail = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
