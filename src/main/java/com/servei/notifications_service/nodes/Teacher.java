package com.servei.notifications_service.nodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tanin on 20/02/2019.
 */
@NodeEntity
public class Teacher {
    @Id @GeneratedValue private Long id;
    private String DNI, name, surname, mail, phoneNum;

    @Relationship(type = "RECEIVE", direction = Relationship.UNDIRECTED)
    @JsonProperty
    private Set<Notification> notifications;

    public Teacher(){}

    public void receiveNotifications(Notification notification){
        if(this.notifications == null){
            this.notifications = new HashSet<>();
        }
        this.notifications.add(notification);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }



    @Override
    public String toString() {
        return "teacher{" +
                "id=" + id +
                ", DNI='" + DNI + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", notifications=" + notifications +
                '}';
    }
}
