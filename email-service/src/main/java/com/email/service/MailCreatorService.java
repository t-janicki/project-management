package com.email.service;

import com.email.InviteInfo;
import com.email.PasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Value("${app.frontEndUrl}")
    private String frontEndUrl;

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    public MailCreatorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildInvitationEmail(InviteInfo inviteInfo) {

        Context context = new Context();
        context.setVariable("button", "Join team");
        context.setVariable("invite_info", inviteInfo);
        return templateEngine.process("team-invitation-mail", context);
    }

    public String buildPasswordResetEmail(PasswordToken passwordToken) {
        PasswordToken link = new PasswordToken(
                passwordToken.getToken(),
                frontEndUrl + "/reset-password?token="
        );

        Context context = new Context();
        context.setVariable("button", "Reset password");
        context.setVariable("password_token", link);
        return templateEngine.process("reset-password-mail", context);
    }
}

