package com.servei.notifications_service.services.socket_notificator;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SocketListener {

    @Value("${socket.io.port}")
    private Integer socketIOPort;

    @Value("${socket.io.hostname}")
    private String socketIOHostname;

    @Autowired
    private Registers registers;

    //Start socket connection with client
    @Bean
    public CommandLineRunner runner(){

        return args -> {
            Configuration config = new Configuration();
            config.setHostname(socketIOHostname);
            config.setPort(socketIOPort);

            final SocketIOServer server = new SocketIOServer(config);

            server.addEventListener("register", Register.class, (client, data, ackRequest) -> {
                registers.updateTeacher(data.getEmail(), client);
            });


            server.start();

        };
    }
}
