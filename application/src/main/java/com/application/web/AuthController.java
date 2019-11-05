package com.application.web;

import com.account.domain.User;
import com.account.service.UserService;
import com.application.facade.UserAuthFacade;
import com.email.Mail;
import com.email.PasswordToken;
import com.email.service.SimpleEmailService;
import com.utility.exception.UnauthorizedException;
import com.utility.web.request.user.LoginRequest;
import com.utility.web.request.user.PasswordReset;
import com.utility.web.request.user.SignUpRequest;
import com.utility.web.response.ApiResponse;
import com.utility.web.response.user.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
    private UserAuthFacade userAuthFacade;
    private SimpleEmailService simpleEmailService;

    @Autowired
    public AuthController(UserService userService,
                          UserAuthFacade userAuthFacade,
                          SimpleEmailService simpleEmailService) {
        this.userService = userService;
        this.userAuthFacade = userAuthFacade;
        this.simpleEmailService = simpleEmailService;
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userAuthFacade.authenticateUser(loginRequest));
    }

    @PostMapping(value = "/signup", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = userService.registerUser(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }


    @PostMapping(value = "/forgot-password", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ApiResponse forgotPasswordToken(@RequestParam String email) {

        String token = userAuthFacade.generatePasswordResetToken(email);

        simpleEmailService.sendPasswordResetMail(new Mail(email, "Password Reset"), new PasswordToken(token));

        return new ApiResponse(true, "Email with token sended. ");
    }

    @PostMapping(value = "/reset-password", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ApiResponse resetPassword(@RequestParam String token,
                                     @RequestBody PasswordReset passwordReset) {
        try {
            return userAuthFacade.resetPassword(token, passwordReset);
        } catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Token expired");
        }
    }
}