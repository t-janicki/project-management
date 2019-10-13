package com.utility.dto.scrumboard;

public class ActivityDTO {
    private Long id;
    private String type;
    private Long userId;
    private String message;
    private String time;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String type, Long userId, String message, String time) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.message = message;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
