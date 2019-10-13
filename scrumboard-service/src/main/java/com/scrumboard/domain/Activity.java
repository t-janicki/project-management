package com.scrumboard.domain;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    private Long userId;

    @Column
    private String message;

    @Column
    private Long time;

    public Activity() {
    }

    public Activity(Long id, String type, Long userId, String message) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
