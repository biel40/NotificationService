package com.servei.notifications_service.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Notification {
    @Id @GeneratedValue private Long id;
    private Date date;
    private boolean sent;

    @Relationship(type = "BELONGS_TO", direction = Relationship.UNDIRECTED)
    private Set<Student> students;

    @Relationship(type = "SEND_BY", direction = Relationship.UNDIRECTED)
    private Set<Provider> providers;

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

    public void belongsToStudent(Student student){
        if(this.students == null){
            this.students = new HashSet<>();
        }
        this.students.add(student);
    }

    public void sendByProvider(Provider provider){
        if(this.providers == null){
            this.providers = new HashSet<>();
        }
        this.providers.add(provider);
    }

}
