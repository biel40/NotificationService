package com.servei.notifications_service.MailProvider;

import com.servei.notifications_service.nodes.Teacher;

public interface MailProvider {

    void configure();
    boolean sendMail(Teacher teacher);
    void updateNotifications(Teacher teacher);
}
