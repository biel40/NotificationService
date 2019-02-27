package com.servei.notifications_service.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.nodes.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MailgunService extends MailProviderDecorator {

    private String mailgunApiKey;
    private String mailgunApiUrl;
    private Formatter formatter;

    @Autowired
    public MailgunService(MailProvider mailProvider,
                          Formatter formatter,
                          @Qualifier("mailgunAPIkey") String mailgunApiKey,
                          @Qualifier("mailgunAPIurl") String mailgunApiUrl) {
        super(mailProvider);
        this.mailgunApiKey = mailgunApiKey;
        this.mailgunApiUrl = mailgunApiUrl;
        this.formatter = formatter;
    }

    @Override
    public boolean sendNotifications(Teacher teacher) {
        super.sendNotifications(teacher);
        HttpResponse<JsonNode> request = null;

        try {
            request = Unirest.post(mailgunApiUrl)
                    .basicAuth("api", mailgunApiKey)
                    .field("from", "")
                    .field("to", teacher.getMail())
                    .field("subject", "" + teacher.getName() + " " + teacher.getSurname())
                    .field("html", formatter)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (request == null) {
            throw new NullPointerException("Error sending notifications with Mailgun");
        }

        return request.getStatusText().equals("OK");
    }
}
