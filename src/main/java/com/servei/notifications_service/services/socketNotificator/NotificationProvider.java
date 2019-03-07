package com.servei.notifications_service.services.socketNotificator;

import com.servei.notifications_service.nodes.Teacher;
import com.servei.notifications_service.services.NotificationError;
import org.springframework.stereotype.Component;

import java.util.List;

public interface NotificationProvider {
    List<NotificationError> sendNotifications(Teacher teacher);
}
