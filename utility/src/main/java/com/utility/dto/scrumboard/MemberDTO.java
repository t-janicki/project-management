package com.utility.dto.scrumboard;

public class MemberDTO {
    private Long id;
    private String name;
    private String avatarUrl;
    private Long userId;

    public MemberDTO() {
    }

    public MemberDTO(Long id, String name, String avatarUrl, Long userId) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }
}
