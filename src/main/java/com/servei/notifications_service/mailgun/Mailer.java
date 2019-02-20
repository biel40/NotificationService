package com.servei.notifications_service.mailgun;
import net.sargue.mailgun.Mail;

public class Mailer {
    private Body emailBody;

    public Mailer() {

    }

    public Mailer(Body emailBody) {
        this.emailBody = emailBody;
    }

    public void send() {
        Mail.using(emailBody.getConfiguration())
                .to(emailBody.getReceiver())
                .subject(emailBody.getSubject())
                .text(emailBody.getNotification().toString())
                .build()
                .send();
        System.out.println("Email sent");
    }

    public void setEmailBody(Body emailBody) {
        this.emailBody = emailBody;
    }
}
