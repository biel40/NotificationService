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
public class Alumne {
    @Id @GeneratedValue private Long id;
    private String DNI;
    private String name;
    private String surname;

    private Alumne(){
    }

    public Alumne(String DNI, String name, String surname){
        this.DNI = DNI;
        this.name = name;
        this.surname = surname;
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

    @Relationship(type = "HASFAULT", direction = Relationship.UNDIRECTED)
    public Set<Falta> faltes;

    public void teFaltes(Falta falta){
        if(faltes == null){
            faltes = new HashSet<>();
        }
        faltes.add(falta);
    }

}


