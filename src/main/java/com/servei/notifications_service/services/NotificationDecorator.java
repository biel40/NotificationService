package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationDecorator implements NotificationProvider {

    private List<NotificationProvider> allProviders;

    @Autowired
    public NotificationDecorator(List<NotificationProvider> notificationProviders){
        if (notificationProviders != null && !notificationProviders.isEmpty()) {
            allProviders.addAll(notificationProviders);
        }
    }

    @Override
    public List<NotificationError> sendNotifications(Teacher teacher) {
        List<NotificationError> results = new ArrayList<>();

        for (NotificationProvider notificationProvider : allProviders){
            List<NotificationError> result = notificationProvider.sendNotifications(teacher);

            if (!result.isEmpty()){
                results.addAll(result);
            }
        }

        return results;
    }
}
