package com.application.web;

import com.chat.domain.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Chat getChatTest() {
        Chat chat = new Chat(1L);

        Message message1 = new Message(1L);
        message1.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        message1.setTime(new Date());
        message1.setUnread(Boolean.FALSE);

        Message message2 = new Message(2L);
        message2.setMessage("We are losing money! Quick!");
        message2.setTime(new Date());
        message2.setUnread(Boolean.FALSE);

        Message message3 = new Message(3L);
        message3.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        message3.setTime(new Date());
        message3.setUnread(Boolean.TRUE);

        Message message4 = new Message(4L);
        message4.setMessage("You are the worst!");
        message4.setTime(new Date());
        message4.setUnread(Boolean.FALSE);

        Message message5 = new Message(5L);
        message5.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        message5.setTime(new Date());
        message5.setUnread(Boolean.TRUE);

        Message message6 = new Message(6L);
        message6.setMessage("We are losing money! Quick!");
        message6.setTime(new Date());
        message6.setUnread(Boolean.FALSE);

        Message message7 = new Message(7L);
        message7.setMessage("It’s not my money, you know. I will eat my breakfast and then I will come to the meeting room.");
        message7.setTime(new Date());
        message7.setUnread(Boolean.TRUE);

        Dialog dialog1 = new Dialog(1L);
        dialog1.setContactId(1L);
        dialog1.setLastMessageTime(new Date());
        dialog1.setMessages(Arrays.asList(message1, message2, message3, message4, message5, message6, message7));


        Message message8 = new Message(8L);
        message8.setMessage("We are losing brain! Quick!");
        message8.setTime(new Date());
        message8.setUnread(Boolean.FALSE);

        Message message9 = new Message(9L);
        message9.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        message9.setTime(new Date());
        message9.setUnread(Boolean.FALSE);

        Message message10 = new Message(10L);
        message10.setMessage("We are losing brain! Quick!");
        message10.setTime(new Date());
        message10.setUnread(Boolean.TRUE);

        Message message11 = new Message(11L);
        message11.setMessage("I’m having breakfast right now, can’t you wait for 10 minutes?");
        message11.setTime(new Date());
        message11.setUnread(Boolean.FALSE);

        Dialog dialog2 = new Dialog(2L);
        dialog2.setContactId(2L);
        dialog2.setLastMessageTime(new Date());
        dialog2.setMessages(Arrays.asList(message8, message9, message10, message11, message5, message6, message7));

        chat.setDialog(Arrays.asList(dialog1, dialog2));
        chat.setUnreadMessages(1L);

        return chat;
    }

    @GetMapping(value = "contacts", produces = APPLICATION_JSON_VALUE)
    public List<Contact> getContactsTest() {
        Contact contact1 = new Contact(1L);
        contact1.setAvatarUrl("/assets/images/avatar.jpg");
        contact1.setDisplayName("Tomasz Janicki 1");
        contact1.setStatus(Status.ONLINE);
        contact1.setMood("Will be amazing day");

        Contact contact2 = new Contact(2L);
        contact2.setAvatarUrl("/assets/images/avatar.jpg");
        contact2.setDisplayName("Tomasz Janicki 2");
        contact2.setStatus(Status.ONLINE);
        contact2.setMood("Will be amazing day");

        Contact contact3 = new Contact(3L);
        contact3.setAvatarUrl("/assets/images/avatar.jpg");
        contact3.setDisplayName("Tomasz Janicki 3");
        contact3.setStatus(Status.ONLINE);
        contact3.setMood("Will be amazing day");

        Contact contact4 = new Contact(4L);
        contact4.setAvatarUrl("/assets/images/avatar.jpg");
        contact4.setDisplayName("Tomasz Janicki 4");
        contact4.setStatus(Status.ONLINE);
        contact4.setMood("Will be amazing day");

        Contact contact5 = new Contact(5L);
        contact5.setAvatarUrl("/assets/images/avatar.jpg");
        contact5.setDisplayName("Tomasz Janicki 5");
        contact5.setStatus(Status.ONLINE);
        contact5.setMood("Will be amazing day");

        Contact contact6 = new Contact(6L);
        contact6.setAvatarUrl("/assets/images/avatar.jpg");
        contact6.setDisplayName("Tomasz Janicki");
        contact6.setStatus(Status.ONLINE);
        contact6.setMood("Will be amazing day");

        Contact contact7 = new Contact(7L);
        contact7.setAvatarUrl("/assets/images/avatar.jpg");
        contact7.setDisplayName("Tomasz Janicki");
        contact7.setStatus(Status.ONLINE);
        contact7.setMood("Will be amazing day");

        Contact contact8 = new Contact(8L);
        contact8.setAvatarUrl("/assets/images/avatar.jpg");
        contact8.setDisplayName("Tomasz Janicki");
        contact8.setStatus(Status.ONLINE);
        contact8.setMood("Will be amazing day");

        Contact contact9 = new Contact(9L);
        contact9.setAvatarUrl("/assets/images/avatar.jpg");
        contact9.setDisplayName("Tomasz Janicki");
        contact9.setStatus(Status.ONLINE);
        contact9.setMood("Will be amazing day");

        Contact contact10 = new Contact(10L);
        contact10.setAvatarUrl("/assets/images/avatar.jpg");
        contact10.setDisplayName("Tomasz Janicki");
        contact10.setStatus(Status.ONLINE);
        contact10.setMood("Will be amazing day");

        Contact contact11 = new Contact(11L);
        contact11.setAvatarUrl("/assets/images/avatar.jpg");
        contact11.setDisplayName("Tomasz Janicki");
        contact11.setStatus(Status.ONLINE);
        contact11.setMood("Will be amazing day");

        return Arrays.asList(contact1, contact2, contact3, contact4, contact5,
                contact6, contact7, contact8, contact9, contact10, contact11);

    }
}
