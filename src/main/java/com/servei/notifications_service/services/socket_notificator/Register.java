package com.servei.notifications_service.services.socket_notificator;

import org.springframework.stereotype.Component;

@Component
public class Register {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
