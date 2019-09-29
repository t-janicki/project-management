package com.account.service;

import com.account.domain.layout.Settings;

public interface LayoutSettingsService {

    Settings assignLayoutSettings(Long id);

    Settings createDefaultLayoutSettings();

    Settings updateSettings(Settings settings);
}
