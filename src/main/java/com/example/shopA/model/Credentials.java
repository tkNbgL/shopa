package com.example.shopA.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Credentials {
    @Id
    private String id;
    @NonNull
    private String mailAddress;
    @NonNull
    private String password;

    public Credentials() {}

    public Credentials(@NonNull String mailAddress, @NonNull String password) {
        this.mailAddress = mailAddress;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(@NonNull String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
