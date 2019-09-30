package com.utility.web.response.user;

import com.utility.web.response.user.layout.SettingsDTO;

public class AuthResponse {
    private UserResponse userDetails;
    private SettingsDTO settings;
    private String[] shortcuts;
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponse() {
    }

    public AuthResponse(UserResponse userDetails, SettingsDTO settings,
                        String[] shortcuts, String accessToken) {
        this.userDetails = userDetails;
        this.settings = settings;
        this.shortcuts = shortcuts;
        this.accessToken = accessToken;
    }

    public AuthResponse(UserResponse userDetails, SettingsDTO settings,
                        String[] shortcuts) {
        this.userDetails = userDetails;
        this.settings = settings;
        this.shortcuts = shortcuts;
    }

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public UserResponse getUserDetails() {
        return userDetails;
    }

    public SettingsDTO getSettings() {
        return settings;
    }

    public String[] getShortcuts() {
        return shortcuts;
    }
}