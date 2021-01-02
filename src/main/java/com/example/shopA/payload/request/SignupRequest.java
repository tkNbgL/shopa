package com.example.shopA.payload.request;

import org.springframework.lang.NonNull;

public class SignupRequest {
    @NonNull
    private String shopname;
    @NonNull
    private String email;
    @NonNull
    private String password;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(@NonNull String shopname) {
        this.shopname = shopname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
