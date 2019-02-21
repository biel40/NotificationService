package com.servei.notifications_service;

import java.util.Date;

public class SentMail {

    private String id;
    private Date date;
    private String provider;
    private boolean sent;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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
}
