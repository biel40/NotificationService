package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;

public interface MailProvider {
    boolean sendNotifications(Teacher teacher);
}
