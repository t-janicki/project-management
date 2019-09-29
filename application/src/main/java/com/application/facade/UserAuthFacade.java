package com.application.facade;

import com.account.domain.User;
import com.account.service.LayoutShortcutService;
import com.application.mapper.SettingsMapper;
import com.auth.service.AuthService;
import com.utility.web.request.user.LoginRequest;
import com.utility.web.response.user.AuthResponse;
import com.utility.web.response.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.application.mapper.RoleMapper.roleToString;

@Component
public final class UserAuthFacade {
    private AuthService authService;
    private LayoutShortcutService shortcutService;
    private SettingsMapper settingsMapper;

    @Autowired
    public UserAuthFacade(AuthService authService,
                          LayoutShortcutService shortcutService,
                          SettingsMapper settingsMapper) {
        this.authService = authService;
        this.shortcutService = shortcutService;
        this.settingsMapper = settingsMapper;
    }

    public AuthResponse authenticateUser(LoginRequest request) {
        String token = authService.authenticateUser(request).getAccessToken();

        User user = authService.getUserFromToken(token);

        String[] shortcuts = shortcutService.getLayoutShortcuts(user.getId());

        UserResponse userDetailsResponse = new UserResponse(
                user.getId(),
                roleToString(user),
                user.getName(),
                user.getSettings().getPhotoURL(),
                user.getEmail(),
                user.getPhone()
        );

        return new AuthResponse(
                userDetailsResponse,
                settingsMapper.mapToSettingsDTO(user.getSettings()),
                shortcuts,
                token
        );
    }
}
