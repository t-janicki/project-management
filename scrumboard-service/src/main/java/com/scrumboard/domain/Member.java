package com.scrumboard.domain;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String avatarUrl;

    @Column
    private Long userId;

    public Member() {
    }

    public Member(String name, String avatarUrl, Long userId) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.userId = userId;
    }

    public Member(Long id, String name, String avatarUrl, Long userId) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.userId = userId;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
