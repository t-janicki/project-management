package com.email.service;

import com.email.InviteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
//    private InviteInfo inviteInfo;

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    @Autowired
    public MailCreatorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildInvitationEmail(InviteInfo inviteInfo) {

        Context context = new Context();
//        context.setVariable("message", message);
        context.setVariable("button", "Join team");
        context.setVariable("invite_info", inviteInfo);
        return templateEngine.process("team-invitation-mail", context);
    }
}

