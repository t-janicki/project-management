package com.utility.dto.scrumboard;

import java.util.List;

public class CheckListDTO {
    private Long id;
    private String name;
    private Boolean isDeleted;
    private List<CheckItemDTO> checkItems;

    public CheckListDTO() {

    }

    public CheckListDTO(Long id, String name, Boolean isDeleted, List<CheckItemDTO> checkItems) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.checkItems = checkItems;
    }

    public CheckListDTO(Long id, String name, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public List<CheckItemDTO> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<CheckItemDTO> checkItems) {
        this.checkItems = checkItems;
    }
}
