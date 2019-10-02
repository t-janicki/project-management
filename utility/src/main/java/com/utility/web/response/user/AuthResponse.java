package com.utility.web.response.user;

import com.utility.dto.settings.SettingsDTO;

public class AuthResponse {
    private UserInfoResponse userInfo;
    private SettingsDTO settings;
    private String[] shortcuts;
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponse() {
    }

    public AuthResponse(UserInfoResponse userInfo, SettingsDTO settings,
                        String[] shortcuts, String accessToken) {
        this.userInfo = userInfo;
        this.settings = settings;
        this.shortcuts = shortcuts;
        this.accessToken = accessToken;
    }

    public AuthResponse(UserInfoResponse userInfo, SettingsDTO settings,
                        String[] shortcuts) {
        this.userInfo = userInfo;
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

    public UserInfoResponse getUserInfo() {
        return userInfo;
    }

    public SettingsDTO getSettings() {
        return settings;
    }

    public String[] getShortcuts() {
        return shortcuts;
    }
}