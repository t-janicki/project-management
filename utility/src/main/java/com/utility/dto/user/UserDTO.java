package com.utility.dto.user;

public final class UserDTO {
//    private String createdAt;
//    private String updatedAt;
//    private String createdByUserId;
//    private String updatedByUserId;
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String jobTitle;
    private String imageUrl;
    private Boolean isActive;
    private Boolean emailVerified;
    private String role;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email,
                   String phone, String jobTitle, String imageUrl,
                   Boolean isActive, Boolean emailVerified, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.emailVerified = emailVerified;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getRole() {
        return role;
    }
}
