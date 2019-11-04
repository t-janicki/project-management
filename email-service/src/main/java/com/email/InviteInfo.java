package com.email;

import org.springframework.stereotype.Component;

@Component
public class InviteInfo {
    private String email;
    private String teamName;
    private String teamUrl;

    public InviteInfo() {

    }

    public InviteInfo(String email, String teamName, String teamUrl) {
        this.email = email;
        this.teamName = teamName;
        this.teamUrl = teamUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamUrl() {
        return teamUrl;
    }
}
