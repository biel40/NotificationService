package com.servei.notifications_service.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * Created by tanin on 20/02/2019.
 */
@NodeEntity
public class Notification {
    @Id @GeneratedValue private Long id;
    private Date date;
    private boolean sent;

    private Notification(){

    }

    public Notification(Date date, boolean sent){
        this.date = date;
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "Notification " + this.date.toString();
    }
}
