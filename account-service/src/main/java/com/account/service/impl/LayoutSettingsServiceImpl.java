package com.account.service.impl;


import com.account.domain.User;
import com.account.domain.layout.Settings;
import com.account.domain.layout.settings.Layout;
import com.account.domain.layout.settings.layout.Config;
import com.account.domain.layout.settings.layout.config.Footer;
import com.account.domain.layout.settings.layout.config.Navbar;
import com.account.domain.layout.settings.layout.config.Toolbar;
import com.account.domain.layout.settings.theme.Theme;
import com.account.repository.UserRepository;
import com.account.repository.layout.LayoutSettingsRepository;
import com.account.service.LayoutSettingsService;
import com.utility.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LayoutSettingsServiceImpl implements LayoutSettingsService {
    private UserRepository userRepository;
    private LayoutSettingsRepository settingsRepository;

    @Autowired
    public LayoutSettingsServiceImpl(
            UserRepository userRepository,
                                     LayoutSettingsRepository settingsRepository) {
        this.userRepository = userRepository;
        this.settingsRepository =settingsRepository;
    }

    public Settings createDefaultLayoutSettings() {
        return new Settings(
                "assets/images/avatars/profile.jpg",

                Boolean.TRUE,

                new Layout(
                        "layout1",
                        new Config(
                                "content",
                                "fullwidth",
                                new Navbar(
                                        Boolean.TRUE,
                                        Boolean.TRUE,
                                        "left"
                                ),
                                new Toolbar(
                                        Boolean.TRUE,
                                        "fixed",
                                        "below"
                                ),
                                new Footer(
                                        Boolean.TRUE,
                                        "fixed",
                                        "below"
                                )
                        )
                ),
                new Theme(
                        "defaultDark",
                        "defaultDark",
                        "defaultDark",
                        "defaultDark"
                )
        );
    }

    public Settings assignLayoutSettings(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Settings settings = createDefaultLayoutSettings();

        user.setSettings(createDefaultLayoutSettings());

        userRepository.save(user);

        return settings;
    }

    public Settings updateSettings(Settings settings) {
        Settings result = settingsRepository.findById(settings.getId())
                .orElseThrow(() -> new NotFoundException("Settings not found."));

        result.setCustomScrollbars(settings.getCustomScrollbars());
        result.setLayout(settings.getLayout());
        result.setTheme(settings.getTheme());

        settingsRepository.save(result);

        return result;
    }

}
