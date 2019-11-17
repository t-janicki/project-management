package com.chat.domain;

import javax.persistence.*;

@Entity
@Table(name = "chat_users")
public class ChatUser extends AbstractUser {

    public ChatUser() {
    }

    public ChatUser(Long id) {
        super(id);
    }
}
