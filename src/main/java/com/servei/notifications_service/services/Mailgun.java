package com.servei.notifications_service.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.models.Constants;
import com.servei.notifications_service.nodes.Teacher;

public class Mailgun extends Provider {

    public Mailgun(String sender, String subject, String provider) {
        super(sender, subject, provider);
    }

    @Override
    public void sendNotification(Teacher teacher) throws UnirestException {
        getFormatter().setTeacher(teacher);
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + Constants.MAILGUN_DOMAIN + "/messages")
                .basicAuth("api", Constants.API_MAILGUN)
                .field("from", getSender())
                .field("to", teacher.getMail())
                .field("subject", getSubject() + teacher.getName() + " " + teacher.getSurname())
                .field("html", getFormatter().toHtml())
                .asJson();
        if (request.getStatusText().equals("OK")) {
            this.updateNotifications(teacher);
        }
    }

    @Override
    public void updateNotifications(Teacher teacher) {
        teacher.getNotifications().forEach(notification -> notification.setItWasSent(true));
    }


}
