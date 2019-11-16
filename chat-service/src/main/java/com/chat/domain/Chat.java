package com.chat.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long unreadMessages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dialog> dialog;

    public Chat() {
    }

    public Chat(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(Long unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public List<Dialog> getDialog() {
        return dialog;
    }

    public void setDialog(List<Dialog> dialog) {
        this.dialog = dialog;
    }
}
