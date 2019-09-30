package com.utility.web.response.user;

public class UserInfoResponse {
    //    private Long createdAt;
//    private Long updatedAt;
//    private String createdByUserId;
//    private String updatedByUserId;
    private Long id;
    private String role;
    private String name;
    private String imageUrl;
    private String email;
    private String phone;

    public UserInfoResponse() {
    }

    public UserInfoResponse(Long id, String role,
                            String name, String imageUrl,
                            String email, String phone) {

        this.id = id;
        this.role = role;
        this.name = name;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

}
