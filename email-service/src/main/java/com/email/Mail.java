package com.email;

public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String toCc;

    public Mail() {
    }

    public Mail(String mailTo, String subject) {
        this.mailTo = mailTo;
        this.subject = subject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getToCc() {
        return toCc;
    }
}
