package com.scrumboard.web;

public class InviteRequest {
    private Long teamId;
    private String email;

    public InviteRequest() {
    }

    public InviteRequest(Long teamId, String email) {
        this.teamId = teamId;
        this.email = email;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getEmail() {
        return email;
    }
}
