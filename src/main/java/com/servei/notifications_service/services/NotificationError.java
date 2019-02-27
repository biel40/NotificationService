package com.servei.notifications_service.services;

import org.springframework.stereotype.Component;

@Component
public class NotificationError {

    private NotificationProvider notificationProvider;
    private String description;

    public NotificationProvider getNotificationProvider() {
        return notificationProvider;
    }

    void setNotificationProvider(NotificationProvider notificationProvider) {
        this.notificationProvider = notificationProvider;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NotificationError{" +
                "notificationProvider=" + notificationProvider +
                ", description='" + description + '\'' +
                '}';
    }
}
