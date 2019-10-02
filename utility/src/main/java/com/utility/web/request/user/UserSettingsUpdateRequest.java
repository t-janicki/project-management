package com.utility.web.request.user;

import com.utility.dto.settings.SettingsDTO;
import com.utility.dto.settings.ShortcutDTO;

import java.util.List;

public final class UserSettingsUpdateRequest {
    private SettingsDTO settings;
    private List<ShortcutDTO> shortcuts;

    public UserSettingsUpdateRequest() {
    }

    public UserSettingsUpdateRequest(SettingsDTO settings,
                                     List<ShortcutDTO> shortcuts) {
        this.settings = settings;
        this.shortcuts = shortcuts;
    }

    public SettingsDTO getSettings() {
        return settings;
    }

    public List<ShortcutDTO> getShortcuts() {
        return shortcuts;
    }
}
