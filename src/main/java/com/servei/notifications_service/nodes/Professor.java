package com.servei.notifications_service.nodes;

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
public class Professor {
    @Id @GeneratedValue private Long id;
    private String DNI, nom, cognom, mail, telefon;

    @Relationship(type = "RECEIVE", direction = Relationship.UNDIRECTED)
    private Set<Notification> notifications;

    private Professor(){

    }

    public Professor(String DNI, String nom, String cognom, String mail, String telefon){
        this.DNI = DNI;
        this.nom = nom;
        this.cognom = cognom;
        this.mail = mail;
        this.telefon = telefon;
    }

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
