package com.servei.notifications_service.mailgun;


public class Mailer {
    private Body emailBody;

    public Mailer() {

    }

    public Mailer(Body emailBody) {
        this.emailBody = emailBody;
    }

    public void send() {

    }

    public void setEmailBody(Body emailBody) {
        this.emailBody = emailBody;
    }
}
