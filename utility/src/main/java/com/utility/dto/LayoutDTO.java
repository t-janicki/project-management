package com.utility.dto;

public final class LayoutDTO {
    private Long id;
    private String style;
    private ConfigDTO config;

    public LayoutDTO() {
    }

    public LayoutDTO(Long id, String style, ConfigDTO config) {
        this.id = id;
        this.style = style;
        this.config = config;
    }

    public Long getId() {
        return id;
    }

    public String getStyle() {
        return style;
    }

    public ConfigDTO getConfig() {
        return config;
    }
}
