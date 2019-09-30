package com.application.facade;

import com.account.domain.layout.Shortcut;
import com.account.service.LayoutSettingsService;
import com.account.service.LayoutShortcutService;
import com.application.mapper.SettingsMapper;
import com.auth.security.UserPrincipal;
import com.utility.web.request.user.UserSettingsUpdateRequest;
import com.utility.web.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UserSettingsFacade {
    private LayoutSettingsService settingsService;
    private LayoutShortcutService shortcutService;
    private SettingsMapper settingsMapper;

    @Autowired
    public UserSettingsFacade(LayoutSettingsService settingsService,
                              LayoutShortcutService shortcutService,
                              SettingsMapper settingsMapper) {
        this.settingsService = settingsService;
        this.shortcutService = shortcutService;
        this.settingsMapper = settingsMapper;
    }

    public ApiResponse updateUserSettings(UserPrincipal userPrincipal, UserSettingsUpdateRequest request) {
        settingsService.updateSettings(settingsMapper.mapToSettings(request.getSettings()));

        List<Shortcut> shortcuts = settingsMapper.mapToShortcutList(request.getShortcuts());

        shortcutService.addLayoutShortcut(userPrincipal.getId(), shortcuts);

        return new ApiResponse(true, "Settings updated.");
    }
}
