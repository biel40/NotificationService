package com.servei.notifications_service.serv;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.servei.notifications_service.nodes.Teacher;

public interface Notificator {
    boolean sendNotification(Teacher teacher) throws UnirestException;
}
