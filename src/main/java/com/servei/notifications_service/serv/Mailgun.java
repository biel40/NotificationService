package com.servei.notifications_service.serv;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.nodes.Teacher;

public class Mailgun implements Notificator {

    private final String DOMAIN = "";
    private final String URL = "https://api.mailgun.net/v3/"+ DOMAIN +"/messages";
    private final String API_KEY = "";

    public Mailgun(String s, String s1, String mailgun) {

    }

    @Override
    public boolean sendNotification(Teacher teacher) throws UnirestException {

        HttpResponse<JsonNode> request = Unirest.post(URL)
                .basicAuth("api", API_KEY)
                .field("from", "")
                .field("to", teacher.getMail())
                .field("subject", "" + teacher.getName() + " " + teacher.getSurname())
                .field("html", "")
                .asJson();

            return request.getStatusText().equals("OK");
    }
}
