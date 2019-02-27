package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NotificationDecorator implements NotificationProvider {

    @Autowired
    private List<NotificationProvider> notificationProviders;


    @Override
    public List<NotificationError> sendNotifications(Teacher teacher) {
        List<NotificationError> results = new ArrayList<>();

        for (NotificationProvider notificationProvider : notificationProviders){
            List<NotificationError> result = notificationProvider.sendNotifications(teacher);

            if (!result.isEmpty()){
                results.addAll(result);
            }

        }

        return results;
    }
}
