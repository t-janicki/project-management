package com.application.facade;

import com.account.domain.User;
import com.account.service.LayoutShortcutService;
import com.account.service.UserService;
import com.application.mapper.SettingsMapper;
import com.auth.security.UserPrincipal;
import com.auth.service.AuthService;
import com.utility.web.request.user.LoginRequest;
import com.utility.web.response.user.AuthResponse;
import com.utility.web.response.user.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.application.mapper.RoleMapper.roleToString;

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
                user.getName(),
                user.getImageUrl(),
                user.getEmail(),
                user.getPhone()
        );

        return new AuthResponse(
                userDetailsResponse,
                settingsMapper.mapToSettingsDTO(user.getSettings()),
                shortcuts
        );
    }
}
