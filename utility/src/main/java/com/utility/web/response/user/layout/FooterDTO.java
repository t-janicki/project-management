package com.utility.web.response.user.layout;

public class FooterDTO {
    private Long id;
    private Boolean display;
    private String style;
    private String position;

    public FooterDTO() {

    }

    public FooterDTO(Long id, Boolean display, String style, String position) {
        this.id = id;
        this.display = display;
        this.style = style;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public Boolean getDisplay() {
        return display;
    }

    public String getStyle() {
        return style;
    }

    public String getPosition() {
        return position;
    }
}
