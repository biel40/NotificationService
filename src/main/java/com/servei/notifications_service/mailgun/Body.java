package com.servei.notifications_service.mailgun;

import com.servei.notifications_service.nodes.Notification;
import net.sargue.mailgun.Configuration;

public class Body {
    private String receiver;
    private String subject;
    private Notification notification;
    private Configuration configuration;

    public Body(String receiver, String subject, Notification notification) {
        this.receiver = receiver;
        this.subject = subject;
        this.notification = notification;
        this.configuration = new Configuration()
                .domain(Constants.DOMAIN)
                .apiKey(Constants.MAILGUN_API)
                .from("Mailgun-Notifier","weeklynotificationesliceu@gmail.com");
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
