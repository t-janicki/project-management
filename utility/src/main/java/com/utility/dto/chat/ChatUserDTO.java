package com.utility.dto.chat;

import java.util.List;

public class ChatUserDTO {
    private Long id;
    private String displayName;
    private String status;
    private String mood;
    private String avatarUrl;
    private List<ChatsDTO> chatList;

    public ChatUserDTO() {

    }

    public ChatUserDTO(Long id, String displayName,
                       String status, String mood,
                       String avatarUrl,
                       List<ChatsDTO> chatList) {
        this.id = id;
        this.displayName = displayName;
        this.status = status;
        this.mood = mood;
        this.avatarUrl = avatarUrl;
        this.chatList = chatList;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getStatus() {
        return status;
    }

    public String getMood() {
        return mood;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<ChatsDTO> getChatList() {
        return chatList;
    }
}
