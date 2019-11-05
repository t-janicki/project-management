package com.email;

public class PasswordToken {
    private String token;
    private String link;

    public PasswordToken() {
    }

    public PasswordToken(String token) {
        this.token = token;
    }

    public PasswordToken(String token, String link) {
        this.token = token;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getToken() {
        return token;
    }
}
