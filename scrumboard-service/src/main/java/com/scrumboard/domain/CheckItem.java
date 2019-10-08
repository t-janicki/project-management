package com.scrumboard.domain;

import javax.persistence.*;

@Entity
@Table(name = "check_items")
public class CheckItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Boolean isChecked;

    @Column
    private Boolean isDeleted;

    public CheckItem() {
    }

    public CheckItem(Long id, String name, Boolean isChecked, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.isChecked = isChecked;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
