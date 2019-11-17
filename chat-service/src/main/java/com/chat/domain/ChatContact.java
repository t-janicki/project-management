package com.chat.domain;

import javax.persistence.*;

@Entity
@Table(name = "chat_contacts")
public class ChatContact extends AbstractUser {

    public ChatContact() {

    }

    public ChatContact(Long id) {
        super(id);
    }
}
