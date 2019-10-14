package com.utility.dto.scrumboard;

public class ActivityDTO {
    private Long id;
    private String type;
    private String userName;
    private String avatarUrl;
    private String message;
    private String time;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String type, String userName,
                       String avatarUrl, String message, String time) {
        this.id = id;
        this.type = type;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.message = message;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
