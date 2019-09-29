package com.utility.web.response.user;

public class UserResponse {
    //    private Long createdAt;
//    private Long updatedAt;
//    private String createdByUserId;
//    private String updatedByUserId;
    private Long id;
    private String role;
    private String name;
    private String photoURL;
    private String email;
    private String phone;

    public UserResponse() {
    }

    public UserResponse(Long id, String role,
                        String name, String photoURL,
                        String email, String phone) {

        this.id = id;
        this.role = role;
        this.name = name;
        this.photoURL = photoURL;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
