package com.servei.notifications_service.serv;

public abstract class Provider implements Notificator {
    private final String sender;// = "Weekly Notifications <weeklynotifications@gmail.com>";
    private final String subject; //= "Weekly notifications for: ";
    private final String provider;
    private final Formatter formatter = new Formatter();

    public Provider(String sender, String subject, String provider){
        this.sender = sender;
        this.subject = subject;
        this.provider = provider;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getProvider() {
        return provider;
    }

    public Formatter getFormatter() {
        return formatter;
    }
}
