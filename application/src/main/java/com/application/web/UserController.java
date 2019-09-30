package com.application.web;


import com.account.domain.User;
import com.account.service.UserService;
import com.application.facade.UserAuthFacade;
import com.auth.security.CurrentUser;
import com.auth.security.UserPrincipal;
import com.utility.web.response.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private UserAuthFacade userAuthFacade;

    @Autowired
    public UserController(UserService userService,
                          UserAuthFacade userAuthFacade) {
        this.userService = userService;
        this.userAuthFacade = userAuthFacade;
    }

//    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
//    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
//        return userService.getUserById(userPrincipal.getId());
//    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public AuthResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userAuthFacade.getUserData(userPrincipal);
    }
}