package com.example.shopA.payload.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer ";
    private String id;
    private String shopname;
    private String email;

    public JwtResponse(String token, String id, String shopname, String email) {
        this.token = token;
        this.id = id;
        this.shopname = shopname;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
