package com.application.mapper;

import com.account.domain.layout.Settings;
import com.account.domain.layout.settings.Layout;
import com.account.domain.layout.settings.layout.Config;
import com.account.domain.layout.settings.layout.config.Footer;
import com.account.domain.layout.settings.layout.config.Navbar;
import com.account.domain.layout.settings.layout.config.Toolbar;
import com.account.domain.layout.settings.theme.Theme;
import com.utility.web.response.user.layout.*;
import org.springframework.stereotype.Component;

@Component
public final class SettingsMapper {

    public SettingsDTO mapToSettingsDTO(Settings settings) {
        return new SettingsDTO(
                settings.getId(),
                settings.getPhotoURL(),
                settings.getCustomScrollbars(),
                this.mapToLayoutDTO(settings.getLayout()),
                this.mapToThemeDTO(settings.getTheme())
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

    private LayoutDTO mapToLayoutDTO(Layout layout) {
        return new LayoutDTO(
                layout.getId(),
                layout.getStyle(),
                this.mapToConfigDTO(layout.getConfig())
        );
    }

    private ConfigDTO mapToConfigDTO(Config config) {
        return new ConfigDTO(
                config.getId(),
                config.getScroll(),
                this.mapToNavbarDTO(config.getNavbar()),
                this.mapToToolbarDTO(config.getToolbar()),
                this.mapToFooterDTO(config.getFooter())
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

    public ToolbarDTO mapToToolbarDTO(Toolbar toolbar) {
        return new ToolbarDTO(
                toolbar.getId(),
                toolbar.getDisplay(),
                toolbar.getStyle(),
                toolbar.getPosition()
        );
    }

    public FooterDTO mapToFooterDTO(Footer footer) {
        return new FooterDTO(
                footer.getId(),
                footer.getDisplay(),
                footer.getStyle(),
                footer.getPosition()
        );
    }
}
