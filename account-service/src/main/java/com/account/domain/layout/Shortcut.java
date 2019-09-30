package com.account.domain.layout;

import com.account.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "shortcuts")
public class Shortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "shortcuts")
    private String shortcut;

    public Shortcut() {
    }

    public Shortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public Shortcut(Long id, String shortcut) {
        this.id = id;
        this.shortcut = shortcut;
    }

    public Long getId() {
        return id;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    @Override
    public String toString() {
        return "Shortcut{" +
                "id=" + id +
                ", shortcut='" + shortcut + '\'' +
                '}';
    }
}
