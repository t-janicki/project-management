package com.application.mapper.user;

import com.account.domain.layout.Settings;
import com.account.domain.layout.Shortcut;
import com.account.domain.layout.settings.Layout;
import com.account.domain.layout.settings.layout.Config;
import com.account.domain.layout.settings.layout.config.Footer;
import com.account.domain.layout.settings.layout.config.Navbar;
import com.account.domain.layout.settings.layout.config.Toolbar;
import com.account.domain.layout.settings.theme.Theme;
import com.utility.dto.settings.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class SettingsMapper {

    public SettingsDTO mapToSettingsDTO(Settings settings) {
        return new SettingsDTO(
                settings.getId(),
                settings.getCustomScrollbars(),
                this.mapToLayoutDTO(settings.getLayout()),
                this.mapToThemeDTO(settings.getTheme())
        );
    }

    public Settings mapToSettings(SettingsDTO settingsDTO) {
        return new Settings(
                settingsDTO.getId(),
                settingsDTO.getCustomScrollbars(),
                this.mapToLayout(settingsDTO.getLayout()),
                this.mapToTheme(settingsDTO.getTheme())
        );
    }

    private Shortcut mapToShortcut(ShortcutDTO shortcutDTO) {
        return new Shortcut(shortcutDTO.getId(), shortcutDTO.getShortcut());
    }

    public List<Shortcut> mapToShortcutList(List<ShortcutDTO> shortcutDTOS) {
        return shortcutDTOS.stream()
                .map(this::mapToShortcut)
                .collect(Collectors.toList());
    }

    private Theme mapToTheme(ThemeDTO themeDTO) {
        return new Theme(
                themeDTO.getId(),
                themeDTO.getMain(),
                themeDTO.getNavbar(),
                themeDTO.getToolbar(),
                themeDTO.getFooter()
        );
    }

    private ThemeDTO mapToThemeDTO(Theme theme) {
        return new ThemeDTO(
                theme.getId(),
                theme.getMain(),
                theme.getNavbar(),
                theme.getToolbar(),
                theme.getFooter()
        );
    }

    private Layout mapToLayout(LayoutDTO layoutDTO) {
        return new Layout(
                layoutDTO.getId(),
                layoutDTO.getStyle(),
                this.mapToConfig(layoutDTO.getConfig())
        );
    }

    private LayoutDTO mapToLayoutDTO(Layout layout) {
        return new LayoutDTO(
                layout.getId(),
                layout.getStyle(),
                this.mapToConfigDTO(layout.getConfig())
        );
    }

    private Config mapToConfig(ConfigDTO configDTO) {
        return new Config(
                configDTO.getId(),
                configDTO.getScroll(),
                configDTO.getMode(),
                this.mapToNavbar(configDTO.getNavbar()),
                this.mapToToolbar(configDTO.getToolbar()),
                this.mapToFooter(configDTO.getFooter())
        );
    }

    private ConfigDTO mapToConfigDTO(Config config) {
        return new ConfigDTO(
                config.getId(),
                config.getScroll(),
                config.getMode(),
                this.mapToNavbarDTO(config.getNavbar()),
                this.mapToToolbarDTO(config.getToolbar()),
                this.mapToFooterDTO(config.getFooter())
        );
    }

    private Navbar mapToNavbar(NavbarDTO navbarDTO) {
        return new Navbar(
                navbarDTO.getId(),
                navbarDTO.getDisplay(),
                navbarDTO.getFolded(),
                navbarDTO.getPosition()
        );
    }

    private NavbarDTO mapToNavbarDTO(Navbar navbar) {
        return new NavbarDTO(
                navbar.getId(),
                navbar.getDisplay(),
                navbar.getFolded(),
                navbar.getPosition()
        );
    }

    private Toolbar mapToToolbar(ToolbarDTO toolbarDTO) {
        return new Toolbar(
                toolbarDTO.getId(),
                toolbarDTO.getDisplay(),
                toolbarDTO.getStyle(),
                toolbarDTO.getPosition()
        );
    }

    private ToolbarDTO mapToToolbarDTO(Toolbar toolbar) {
        return new ToolbarDTO(
                toolbar.getId(),
                toolbar.getDisplay(),
                toolbar.getStyle(),
                toolbar.getPosition()
        );
    }

    private Footer mapToFooter(FooterDTO footerDTO) {
        return new Footer(
                footerDTO.getId(),
                footerDTO.getDisplay(),
                footerDTO.getStyle(),
                footerDTO.getPosition()
        );
    }

    private FooterDTO mapToFooterDTO(Footer footer) {
        return new FooterDTO(
                footer.getId(),
                footer.getDisplay(),
                footer.getStyle(),
                footer.getPosition()
        );
    }
}
