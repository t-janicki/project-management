package com.utility.dto;

public final class SettingsDTO {
    private Long id;
    private Boolean customScrollbars;
    private LayoutDTO layout;
    private ThemeDTO theme;

    public SettingsDTO() {
    }

    public SettingsDTO(Long id, Boolean customScrollbars,
                       LayoutDTO layout, ThemeDTO theme) {
        this.id = id;
        this.customScrollbars = customScrollbars;
        this.layout = layout;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public Boolean getCustomScrollbars() {
        return customScrollbars;
    }

    public LayoutDTO getLayout() {
        return layout;
    }

    public ThemeDTO getTheme() {
        return theme;
    }

}

