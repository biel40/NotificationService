package com.servei.notifications_service.services.socket_notificator;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class Registers {
    private HashMap<String, SocketIOClient> teacherRegisters = new HashMap<>();

    public void updateTeacher(String email, SocketIOClient client){
        this.teacherRegisters.put(email, client);
    }


    public HashMap<String, SocketIOClient> getTeacherRegisters() {
        return teacherRegisters;
    }

    public void setTeacherRegisters(HashMap<String, SocketIOClient> teacherRegisters) {
        this.teacherRegisters = teacherRegisters;
    }
}
