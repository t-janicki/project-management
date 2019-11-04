package com.utility.dto.user;

public final class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String phone;
    private String avatarUrl;
    private Boolean emailVerified;
    private String role;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getRole() {
        return role;
    }
}
