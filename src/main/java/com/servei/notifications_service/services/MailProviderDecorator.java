package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;

public class MailProviderDecorator implements MailProvider {

    private MailProvider mailProvider;

    MailProviderDecorator(MailProvider mailProvider){
        this.mailProvider = mailProvider;
    }

    @Override
    public boolean sendNotifications(Teacher teacher) {
        return this.mailProvider.sendNotifications(teacher);
    }
}
