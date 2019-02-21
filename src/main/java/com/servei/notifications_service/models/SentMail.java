package com.servei.notifications_service.models;

import java.util.Date;

public class SentMail {

    private String id;
    private Date date;
    private String provider;
    private Iterable<Long> idNotifications;
    private boolean sent;

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public boolean isSent(){
        return sent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public Iterable<Long> getIdNotifications() {
        return idNotifications;
    }

    @Override
    public String toString() {
        return "SentMail{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", provider='" + provider + '\'' +
                ", idNotifications=" + idNotifications +
                ", sent=" + sent +
                '}';
    }
}
