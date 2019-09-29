package com.utility.web.response.user.layout;

public class ThemeDTO {
    private Long id;
    private String main;
    private String navbar;
    private String toolbar;
    private String footer;

    public ThemeDTO() {
    }

    public ThemeDTO(Long id, String main, String navbar, String toolbar, String footer) {
        this.id = id;
        this.main = main;
        this.navbar = navbar;
        this.toolbar = toolbar;
        this.footer = footer;
    }

    public Long getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getNavbar() {
        return navbar;
    }

    public String getToolbar() {
        return toolbar;
    }

    public String getFooter() {
        return footer;
    }
}
