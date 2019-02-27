package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;

public class BasicMailProvider implements MailProvider {

    @Override
    public boolean sendNotifications(Teacher teacher) {
        return false;
    }
}
