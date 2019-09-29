package com.utility.web.response.user.layout;

public class SettingsDTO {
    private Long id;
    private String photoURL;
    private Boolean customScrollbars;
    private LayoutDTO layout;
    private ThemeDTO theme;

    public SettingsDTO() {

    }

    public SettingsDTO(Long id, String photoURL,
                       Boolean customScrollbars,
                       LayoutDTO layout, ThemeDTO theme) {
        this.id = id;
        this.photoURL = photoURL;
        this.customScrollbars = customScrollbars;
        this.layout = layout;
        this.theme = theme;
    }

    public Long getId() {
        return id;
    }

    public String getPhotoURL() {
        return photoURL;
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
