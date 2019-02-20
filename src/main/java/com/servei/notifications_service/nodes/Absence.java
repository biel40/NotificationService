package com.servei.notifications_service.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * Created by tanin on 20/02/2019.
 */
@NodeEntity
public class Absence {
    @Id @GeneratedValue private Long id;
    private Date data;
    private String hora;
    private String assignatura;

    private Absence(){
    }

    public Absence(Date data, String hora, String assignatura){
        this.data = data;
        this.hora = hora;
        this.assignatura = assignatura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAssignatura() {
        return assignatura;
    }

    public void setAssignatura(String assignatura) {
        this.assignatura = assignatura;
    }
}
