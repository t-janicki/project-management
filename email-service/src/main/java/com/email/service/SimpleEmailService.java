package com.email.service;

import com.email.InviteInfo;
import com.email.Mail;
import com.email.PasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    private JavaMailSender javaMailSender;
    private MailCreatorService mailCreatorService;

    @Autowired
    public SimpleEmailService(JavaMailSender javaMailSender,
                              MailCreatorService mailCreatorService) {
        this.javaMailSender = javaMailSender;
        this.mailCreatorService = mailCreatorService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void sendInvitationMessage(final Mail mail, InviteInfo inviteInfo) {

        LOGGER.info("Starting email preparation...");
        try {

            javaMailSender.send(createInvitationMessage(mail, inviteInfo));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {

            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    public void sendPasswordResetMail(final Mail mail, PasswordToken passwordToken) {
        LOGGER.info("Starting email preparation...");
        try {

            javaMailSender.send(createPasswordEmail(mail, passwordToken));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {

            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createInvitationMessage(final Mail mail, InviteInfo inviteInfo) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildInvitationEmail(inviteInfo), true);
        };
    }

    private MimeMessagePreparator createPasswordEmail(final Mail mail, PasswordToken passwordToken) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildPasswordResetEmail(passwordToken), true);
        };
    }
}