package com.servei.notifications_service.services;

import com.servei.notifications_service.nodes.Teacher;
import com.servei.notifications_service.repositories.NotificationRepository;
import com.servei.notifications_service.services.socketNotificator.NotificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationDecorator implements NotificationProvider {

    @Autowired
    private List<NotificationProvider> allProviders;

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<NotificationError> sendNotifications(Teacher teacher) {
        List<NotificationError> results = new ArrayList<>();

        for (NotificationProvider notificationProvider : allProviders){

            List<NotificationError> result = notificationProvider.sendNotifications(teacher);

            if (!result.isEmpty()){
                results.addAll(result);
            }


            //Mark notifications as sent
//            Set<Notification> notificationSet = teacher.getNotifications();
//            for (Notification notification : notificationSet){
//                notification.setItWasSent(true);
//                notificationRepository.save(notification);
//            }
        }

        return results;
    }
}
