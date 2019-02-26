package com.servei.notifications_service.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.models.Constants;
import com.servei.notifications_service.nodes.Teacher;

public class Mailgun implements Notificator {

    private final String sender = "Weekly Notifications <weeklynotifications@gmail.com>";
    private final String subject = "Weekly notifications for: ";
    private final Formatter formatter = new Formatter();

    @Override
    public void sendNotification(Teacher teacher) throws UnirestException {
        formatter.setTeacher(teacher);
        System.out.println(sender + " " + teacher.getMail() + " " + subject);
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + Constants.MAILGUN_DOMAIN + "/messages")
                .basicAuth("api", Constants.API_MAILGUN)
                .field("from", sender)
                .field("to", teacher.getMail())
                .field("subject", subject + teacher.getName() + " " + teacher.getSurname())
                .field("html", formatter.toHtml())
                .asJson();
        System.out.println("Email sent " + request.getBody());
    }


}
