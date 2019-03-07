package com.servei.notifications_service.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.nodes.Teacher;
import com.servei.notifications_service.repositories.NotificationRepository;
import com.servei.notifications_service.services.formaters.Formatter;
import com.servei.notifications_service.services.formaters.HTMLFormat;
import com.servei.notifications_service.services.socketNotificator.NotificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailgunNotificator implements NotificationProvider{
    private String name = "MailGun";

    @Autowired
    NotificationRepository notificationRepository;


    @Value("${mailgun.api.key}") private String mailgunAPIkey;
    @Value("${mailgun.api.url}") private String mailgunAPIurl;

    private final NotificationError notificationError;
    private final List<NotificationError> result = new ArrayList<>();

    @Autowired
    public MailgunNotificator(NotificationError notificationError) {
        this.notificationError = notificationError;
    }

    @Override
    public List<NotificationError> sendNotifications(Teacher teacher) {
        HttpResponse<JsonNode> request = null;
        Formatter formatter = new HTMLFormat();

        try {
            request = Unirest.post(mailgunAPIurl)
                    .basicAuth("api", mailgunAPIkey)
                    .field("from", "Weekly Notifications <weeklynotifications@gmail.com>")
                    .field("to", teacher.getMail())
                    .field("subject", "" + teacher.getName() + " " + teacher.getSurname())
                    .field("html", formatter.format(teacher))
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (!request.getStatusText().equals("OK")) {
            notificationError.setDescription("Error to send with MailGun");
            notificationError.setNotificationProvider(this);
            result.add(notificationError);
            return result;
        }

        return result;
    }
}
