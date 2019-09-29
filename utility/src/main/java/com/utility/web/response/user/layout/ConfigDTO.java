package com.utility.web.response.user.layout;

public class ConfigDTO {
    private Long id;
    private String scroll;
    private NavbarDTO navbar;
    private ToolbarDTO toolbar;
    private FooterDTO footer;

    public ConfigDTO() {

    }

    public ConfigDTO(Long id, String scroll,
                     NavbarDTO navbar, ToolbarDTO toolbar, FooterDTO footer) {
        this.id = id;
        this.scroll = scroll;
        this.navbar = navbar;
        this.toolbar = toolbar;
        this.footer = footer;
    }

    public Long getId() {
        return id;
    }

    public String getScroll() {
        return scroll;
    }

    public NavbarDTO getNavbar() {
        return navbar;
    }

    public ToolbarDTO getToolbar() {
        return toolbar;
    }

    public FooterDTO getFooter() {
        return footer;
    }
}
