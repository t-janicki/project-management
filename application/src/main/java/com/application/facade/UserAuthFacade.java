package com.application.facade;

import com.account.domain.User;
import com.account.service.LayoutShortcutService;
import com.account.service.UserService;
import com.application.mapper.user.SettingsMapper;
import com.auth.security.UserPrincipal;
import com.auth.service.AuthService;
import com.utility.dto.user.UserDTO;
import com.utility.web.request.user.LoginRequest;
import com.utility.web.request.user.NewPasswordRequest;
import com.utility.web.request.user.PasswordReset;
import com.utility.web.response.ApiResponse;
import com.utility.web.response.user.AuthResponse;
import com.utility.web.response.user.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.application.mapper.user.RoleMapper.roleToString;

@Component
public final class UserAuthFacade {
    private AuthService authService;
    private LayoutShortcutService shortcutService;
    private SettingsMapper settingsMapper;
    private UserService userService;

    @Autowired
    public UserAuthFacade(AuthService authService,
                          LayoutShortcutService shortcutService,
                          SettingsMapper settingsMapper,
                          UserService userService) {
        this.authService = authService;
        this.shortcutService = shortcutService;
        this.settingsMapper = settingsMapper;
        this.userService = userService;
    }

    public AuthResponse authenticateUser(LoginRequest request) {
        String token = authService.authenticateUser(request).getAccessToken();

        Long userId = authService.getUserIdFromToken(token);

        AuthResponse response = getUserData(userId);

        return new AuthResponse(
                response.getUserInfo(),
                response.getSettings(),
                response.getShortcuts(),
                token
        );
    }

    public AuthResponse getUserData(UserPrincipal userPrincipal) {
        return getUserData(userPrincipal.getId());
    }

    private AuthResponse getUserData(Long id) {
        User user = userService.getUserById(id);

        String[] shortcuts = shortcutService.getLayoutShortcuts(user.getId());

        UserInfoResponse userDetailsResponse = new UserInfoResponse(
                user.getId(),
                roleToString(user),
                user.getFirstName(),
                user.getLastName(),
                user.getDisplayName(),
                user.getAvatarUrl(),
                user.getEmail(),
                user.getPhone()
        );

        return new AuthResponse(
                userDetailsResponse,
                settingsMapper.mapToSettingsDTO(user.getSettings()),
                shortcuts
        );
    }

    public ApiResponse newPasswordRequest(Long id, NewPasswordRequest request) {
        return userService.newPasswordRequest(id, request);
    }

    public User updateUser(UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    public String generatePasswordResetToken(String email) {
        String token = authService.createPasswordResetToken(email);

        userService.savePasswordResetToken(email, token);

        return token;
    }

    public ApiResponse resetPassword(String token, PasswordReset passwordReset) {
        authService.validateToken(token);

        return userService.resetPassword(token, passwordReset);
    }
}
