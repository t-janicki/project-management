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

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
