package com.servei.notifications_service.services.socket_notificator;

import com.corundumstudio.socketio.SocketIOClient;
import com.servei.notifications_service.nodes.Notification;
import com.servei.notifications_service.nodes.Teacher;
import com.servei.notifications_service.services.NotificationError;
import com.servei.notifications_service.services.NotificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class SocketNotificator implements NotificationProvider {

    @Autowired
    Registers registers;


    public List<NotificationError> sendNotifications(Teacher teacher) {

        System.out.println(teacher);
        System.out.println(teacher.getNotifications().toString());

        SocketIOClient teacherSocket = registers.getTeacherRegisters().get(teacher.getMail());

        Set<Notification> notificationSet = teacher.getNotifications();

        for(Notification notification : notificationSet){
            //TODO: Null pointer Exception.
            teacherSocket.sendEvent("notification", notification);
            System.out.println("Evento mandado correctamente.");
        }

        return new LinkedList<>();
    }
}
