package com.utility.web.response.user;

public class UserInfoResponse {
    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private String displayName;
    private String avatarUrl;
    private String email;
    private String phone;

    public UserInfoResponse() {
    }

    public UserInfoResponse(Long id, String role, String firstName, String lastName,
                            String displayName, String avatarUrl,
                            String email, String phone) {
        this.id = id;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
