package com.utility.dto.settings;

public final class NavbarDTO {
    private Long id;
    private Boolean display;
    private Boolean folded;
    private String position;

    public NavbarDTO() {
    }

    public NavbarDTO(Long id, Boolean display, Boolean folded, String position) {
        this.id = id;
        this.display = display;
        this.folded = folded;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Boolean getDisplay() {
        return display;
    }

    public Boolean getFolded() {
        return folded;
    }

    public String getPosition() {
        return position;
    }
}
