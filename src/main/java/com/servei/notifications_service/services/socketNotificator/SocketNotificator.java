package com.servei.notifications_service.services.socketNotificator;

import com.corundumstudio.socketio.SocketIOClient;
import com.servei.notifications_service.nodes.Notification;
import com.servei.notifications_service.nodes.Teacher;
import com.servei.notifications_service.repositories.NotificationRepository;
import com.servei.notifications_service.services.NotificationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class SocketNotificator implements NotificationProvider {

    private String name = "SocketIO";

    @Autowired
    private Registers registers;

    @Autowired
    private NotificationRepository notificationRepository;


    public List<NotificationError> sendNotifications(Teacher teacher) {

        //Get teacher socket by email
        SocketIOClient teacherSocket = registers.getTeacherRegisters().get(teacher.getMail());
        Set<Notification> notificationSet = teacher.getNotifications();

        if(teacherSocket != null){
            teacherSocket.sendEvent("notification", notificationSet);
        }

        return new ArrayList<>();
    }
}
