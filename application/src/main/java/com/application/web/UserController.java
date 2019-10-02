package com.application.web;

import com.account.domain.User;
import com.utility.dto.user.UserDTO;
import com.application.facade.UserAuthFacade;
import com.application.facade.UserSettingsFacade;
import com.auth.security.CurrentUser;
import com.auth.security.UserPrincipal;
import com.utility.web.request.user.NewPasswordRequest;
import com.utility.web.request.user.UserSettingsUpdateRequest;
import com.utility.web.response.ApiResponse;
import com.utility.web.response.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
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
    public @ResponseBody
    Resource<AuthResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        AuthResponse response = userAuthFacade.getUserData(userPrincipal);

        Link link = linkTo(UserController.class).slash("me").withSelfRel();

        return new Resource<>(response, link);
    }

    @PutMapping(value = "/me", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public @ResponseBody
    Resource<ApiResponse> updateAccountInfo(@CurrentUser UserPrincipal userPrincipal,
                                            @Valid @RequestBody UserDTO userDTO) {

        User user = userAuthFacade.updateUser(userPrincipal.getId(), userDTO);

        Link link = linkTo(UserController.class).slash(user.getId()).withSelfRel();

        return new Resource<>(new ApiResponse(true, "User updated. "), link);
    }

    @PutMapping(value = "/me/settings", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public @ResponseBody
    ApiResponse updateUserSettings(@CurrentUser UserPrincipal userPrincipal,
                                   @Valid @RequestBody UserSettingsUpdateRequest request) {
        return settingsFacade.updateUserSettings(userPrincipal, request);
    }

    @PutMapping(value = "/me/reset-password", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public @ResponseBody
    ApiResponse newPasswordRequest(@CurrentUser UserPrincipal userPrincipal,
                                   @Valid @RequestBody NewPasswordRequest request) {
        return userAuthFacade.newPasswordRequest(userPrincipal.getId(), request);
    }
}