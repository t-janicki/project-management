package com.utility.dto.chat;

public class ChatsDTO {
    private Long chatId;
    private Long contactId;
    private String lastMessageTime;

    public ChatsDTO() {

    }

    public ChatsDTO(Long chatId, Long contactId, String lastMessageTime) {
        this.chatId = chatId;
        this.contactId = contactId;
        this.lastMessageTime = lastMessageTime;
    }

    public Long getChatId() {
        return chatId;
    }

    public Long getContactId() {
        return contactId;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }
}
