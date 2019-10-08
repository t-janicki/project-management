package com.utility.dto.scrumboard;

public class CheckItemDTO {
    private Long id;
    private String name;
    private Boolean checked;
    private Boolean isDeleted;

    public CheckItemDTO() {
    }

    public CheckItemDTO(Long id, String name, Boolean checked, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.checked = checked;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }
}
