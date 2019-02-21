package com.servei.notifications_service.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Notification {
    @Id @GeneratedValue private Long id;
    private String date;
    private String time;
    private boolean itWasSent;

    @Relationship(type = "BELONGS_TO", direction = Relationship.UNDIRECTED)
    private Set<Student> students;

    @Relationship(type = "SEND_BY", direction = Relationship.UNDIRECTED)
    private Set<Provider> providers;

    public Notification(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isItWasSent() {
        return itWasSent;
    }

    public void setItWasSent(boolean itWasSent) {
        this.itWasSent = itWasSent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Provider> getProviders() {
        return providers;
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

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", itWasSent=" + itWasSent +
                ", students=" + students +
                ", providers=" + providers +
                '}';
    }
}
