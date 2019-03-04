package com.servei.notifications_service.services.socket_notificator;

import com.corundumstudio.socketio.SocketIOClient;
import com.servei.notifications_service.nodes.Notification;
import com.servei.notifications_service.nodes.Teacher;
import com.servei.notifications_service.services.NotificationError;
import com.servei.notifications_service.services.NotificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SocketNotificator implements NotificationProvider {

    @Autowired
    Registers registers;

    @Override
    public List<NotificationError> sendNotifications(Teacher teacher) {

        SocketIOClient teacherSocket = registers.getTeacherRegisters().get(teacher.getMail());

        for(Notification notification : teacher.getNotifications()){
            teacherSocket.sendEvent("notification", notification);
        }


        return null;
    }
}
