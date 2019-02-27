package com.servei.notifications_service.MailProvider;

import com.servei.notifications_service.nodes.Teacher;

public interface MailProvider {
    boolean sendMail(Teacher teacher);
}
