package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;

import java.util.List;

public interface NotificationProvider {
    List<NotificationError> sendNotifications(Teacher teacher);
}
