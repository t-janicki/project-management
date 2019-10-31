package com.utility.dto.scrumboard;

public class MemberDTO {
    private Long id;
    private String name;
    private String avatarUrl;
    private String email;

    public MemberDTO() {
    }

    public MemberDTO(Long id, String name, String avatarUrl, String email) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }
}
