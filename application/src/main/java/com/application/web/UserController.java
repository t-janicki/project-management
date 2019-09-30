package com.application.web;

import com.application.facade.UserAuthFacade;
import com.application.facade.UserSettingsFacade;
import com.auth.security.CurrentUser;
import com.auth.security.UserPrincipal;
import com.utility.web.request.user.UserSettingsUpdateRequest;
import com.utility.web.response.ApiResponse;
import com.utility.web.response.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserAuthFacade userAuthFacade;
    private UserSettingsFacade settingsFacade;

    @Autowired
    public UserController(UserAuthFacade userAuthFacade,
                          UserSettingsFacade settingsFacade) {
        this.userAuthFacade = userAuthFacade;
        this.settingsFacade = settingsFacade;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public AuthResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userAuthFacade.getUserData(userPrincipal);
    }

    @PutMapping(value = "/me/settings", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ApiResponse updateUserSettings(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserSettingsUpdateRequest request) {
        return settingsFacade.updateUserSettings(userPrincipal, request);
    }
}