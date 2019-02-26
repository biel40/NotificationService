package com.servei.notifications_service.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.nodes.Teacher;

public interface Notificator {
    void sendNotification(Teacher teacher) throws UnirestException;
}
