package com.scrumboard.domain;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String className;

    public Label() {
    }

    public Label(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public Label(Long id, String name, String className) {
        this.id = id;
        this.name = name;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
