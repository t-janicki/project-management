package com.application.web;

import com.chat.domain.*;
import com.utility.dto.chat.ChatUserDTO;
import com.utility.dto.chat.ChatsDTO;
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

        ChatMessage chatMessage1 = new ChatMessage(1L);
        chatMessage1.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        chatMessage1.setTime(new Date());
        chatMessage1.setUnread(Boolean.FALSE);

        ChatMessage chatMessage2 = new ChatMessage(2L);
        chatMessage2.setMessage("We are losing money! Quick!");
        chatMessage2.setTime(new Date());
        chatMessage2.setUnread(Boolean.FALSE);

        ChatMessage chatMessage3 = new ChatMessage(3L);
        chatMessage3.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        chatMessage3.setTime(new Date());
        chatMessage3.setUnread(Boolean.TRUE);

        ChatMessage chatMessage4 = new ChatMessage(4L);
        chatMessage4.setMessage("You are the worst!");
        chatMessage4.setTime(new Date());
        chatMessage4.setUnread(Boolean.FALSE);

        ChatMessage chatMessage5 = new ChatMessage(5L);
        chatMessage5.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        chatMessage5.setTime(new Date());
        chatMessage5.setUnread(Boolean.TRUE);

        ChatMessage chatMessage6 = new ChatMessage(6L);
        chatMessage6.setMessage("We are losing money! Quick!");
        chatMessage6.setTime(new Date());
        chatMessage6.setUnread(Boolean.FALSE);

        ChatMessage chatMessage7 = new ChatMessage(7L);
        chatMessage7.setMessage("It’s not my money, you know. I will eat my breakfast and then I will come to the meeting room.");
        chatMessage7.setTime(new Date());
        chatMessage7.setUnread(Boolean.TRUE);

        ChatDialog chatDialog1 = new ChatDialog(1L);
        chatDialog1.setContactId(1L);
        chatDialog1.setLastMessageTime(new Date());
        chatDialog1.setChatMessages(Arrays.asList(chatMessage1, chatMessage2, chatMessage3, chatMessage4, chatMessage5, chatMessage6, chatMessage7));


        ChatMessage chatMessage8 = new ChatMessage(8L);
        chatMessage8.setMessage("We are losing brain! Quick!");
        chatMessage8.setTime(new Date());
        chatMessage8.setUnread(Boolean.FALSE);

        ChatMessage chatMessage9 = new ChatMessage(9L);
        chatMessage9.setMessage("Quickly come to the meeting room 1B, we have a big server issue");
        chatMessage9.setTime(new Date());
        chatMessage9.setUnread(Boolean.FALSE);

        ChatMessage chatMessage10 = new ChatMessage(10L);
        chatMessage10.setMessage("We are losing brain! Quick!");
        chatMessage10.setTime(new Date());
        chatMessage10.setUnread(Boolean.TRUE);

        ChatMessage chatMessage11 = new ChatMessage(11L);
        chatMessage11.setMessage("I’m having breakfast right now, can’t you wait for 10 minutes?");
        chatMessage11.setTime(new Date());
        chatMessage11.setUnread(Boolean.FALSE);

        ChatDialog chatDialog2 = new ChatDialog(2L);
        chatDialog2.setContactId(2L);
        chatDialog2.setLastMessageTime(new Date());
        chatDialog2.setChatMessages(Arrays.asList(chatMessage8, chatMessage9, chatMessage10, chatMessage11, chatMessage5, chatMessage6, chatMessage7));

        chat.setChatDialog(Arrays.asList(chatDialog1, chatDialog2));
        chat.setUnreadMessages(1L);

        return chat;
    }

    @GetMapping(value = "/contacts", produces = APPLICATION_JSON_VALUE)
    public List<ChatContact> getContactsTest() {
        ChatContact chatContact1 = new ChatContact(1L);
        chatContact1.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact1.setDisplayName("Tomasz Janicki 1");
        chatContact1.setChatStatus(ChatStatus.ONLINE);
        chatContact1.setMood("Will be amazing day");

        ChatContact chatContact2 = new ChatContact(2L);
        chatContact2.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact2.setDisplayName("Tomasz Janicki 2");
        chatContact2.setChatStatus(ChatStatus.ONLINE);
        chatContact2.setMood("Will be amazing day");

        ChatContact chatContact3 = new ChatContact(3L);
        chatContact3.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact3.setDisplayName("Tomasz Janicki 3");
        chatContact3.setChatStatus(ChatStatus.ONLINE);
        chatContact3.setMood("Will be amazing day");

        ChatContact chatContact4 = new ChatContact(4L);
        chatContact4.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact4.setDisplayName("Tomasz Janicki 4");
        chatContact4.setChatStatus(ChatStatus.ONLINE);
        chatContact4.setMood("Will be amazing day");

        ChatContact chatContact5 = new ChatContact(5L);
        chatContact5.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact5.setDisplayName("Tomasz Janicki 5");
        chatContact5.setChatStatus(ChatStatus.ONLINE);
        chatContact5.setMood("Will be amazing day");

        ChatContact chatContact6 = new ChatContact(6L);
        chatContact6.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact6.setDisplayName("Tomasz Janicki 6");
        chatContact6.setChatStatus(ChatStatus.ONLINE);
        chatContact6.setMood("Will be amazing day");

        ChatContact chatContact7 = new ChatContact(7L);
        chatContact7.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact7.setDisplayName("Tomasz Janicki 7");
        chatContact7.setChatStatus(ChatStatus.ONLINE);
        chatContact7.setMood("Will be amazing day");

        ChatContact chatContact8 = new ChatContact(8L);
        chatContact8.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact8.setDisplayName("Tomasz Janicki 8");
        chatContact8.setChatStatus(ChatStatus.ONLINE);
        chatContact8.setMood("Will be amazing day");

        ChatContact chatContact9 = new ChatContact(9L);
        chatContact9.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact9.setDisplayName("Tomasz Janicki 9");
        chatContact9.setChatStatus(ChatStatus.ONLINE);
        chatContact9.setMood("Will be amazing day");

        ChatContact chatContact10 = new ChatContact(10L);
        chatContact10.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact10.setDisplayName("Tomasz Janicki 10");
        chatContact10.setChatStatus(ChatStatus.ONLINE);
        chatContact10.setMood("Will be amazing day");

        ChatContact chatContact11 = new ChatContact(11L);
        chatContact11.setAvatarUrl("assets/images/avatars/profile.jpg");
        chatContact11.setDisplayName("Tomasz Janicki 11");
        chatContact11.setChatStatus(ChatStatus.ONLINE);
        chatContact11.setMood("Will be amazing day");

        return Arrays.asList(chatContact1, chatContact2, chatContact3, chatContact4, chatContact5,
                chatContact6, chatContact7, chatContact8, chatContact9, chatContact10, chatContact11);

    }

    @GetMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
    public ChatUserDTO testChatUser() {
        ChatsDTO chatsDTO1 = new ChatsDTO(
                1L,
                1L,
                "17-11-2019"
        );

        ChatsDTO chatsDTO2 = new ChatsDTO(
                2L,
                2L,
                "17-11-2019"
        );

        ChatsDTO chatsDTO3 = new ChatsDTO(
                3L,
                3L,
                "17-11-2019"
        );

        return new ChatUserDTO(
                1L,
                "Tommy",
                "Online",
                "Nice mood for today",
                "assets/images/avatars/profile.jpg",
                Arrays.asList(chatsDTO1, chatsDTO2, chatsDTO3)
        );
    }
}
