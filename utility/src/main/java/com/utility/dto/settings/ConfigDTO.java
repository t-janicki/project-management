package com.utility.dto.settings;

public final class ConfigDTO {
    private Long id;
    private String scroll;
    private String mode;
    private NavbarDTO navbar;
    private ToolbarDTO toolbar;
    private FooterDTO footer;

    public ConfigDTO() {

    }

    public ConfigDTO(Long id, String scroll, String mode,
                     NavbarDTO navbar, ToolbarDTO toolbar, FooterDTO footer) {
        this.id = id;
        this.scroll = scroll;
        this.mode = mode;
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

    public String getMode() {
        return mode;
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
