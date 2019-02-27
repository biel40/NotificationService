package com.servei.notifications_service.services;

public class NotificationError {

    private NotificationProvider notificationProvider;
    private String description;



    public NotificationProvider getNotificationProvider() {
        return notificationProvider;
    }

    public void setNotificationProvider(NotificationProvider notificationProvider) {
        this.notificationProvider = notificationProvider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
