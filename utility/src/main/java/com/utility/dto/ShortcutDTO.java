package com.utility.dto;

public final class ShortcutDTO {
    private Long id;
    private String shortcut;

    public ShortcutDTO(Long id, String shortcut) {
        this.id = id;
        this.shortcut = shortcut;
    }

    /**
     * Constructor necessary for Jackson deserialize
     * ShortcutDTO(String shortcut)
     * */
    public ShortcutDTO(String shortcut) {
        this.shortcut = shortcut;
    }

    public Long getId() {
        return id;
    }

    public String getShortcut() {
        return shortcut;
    }

}
