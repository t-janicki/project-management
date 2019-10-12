package com.utility.dto.scrumboard;

public class ActivityDTO {
    private Long id;
    private String type;
    private String idMember;
    private String message;
    private String time;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String type, String idMember, String message, String time) {
        this.id = id;
        this.type = type;
        this.idMember = idMember;
        this.message = message;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getIdMember() {
        return idMember;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
