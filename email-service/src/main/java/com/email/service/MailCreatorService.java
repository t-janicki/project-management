package com.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
//    @Autowired
//    private AdminConfig adminConfig;

    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    public MailCreatorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildInvitationEmail(String message, String teamInfo) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("team_info", teamInfo);
        context.setVariable("tasks_url", "https://khartaz.github.io/");
        context.setVariable("button", "Join team");
        return templateEngine.process("team-invitation-mail", context);
    }
}

