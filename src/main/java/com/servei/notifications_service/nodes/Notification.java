package com.servei.notifications_service.nodes;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private boolean isRead = false;

    @Relationship(type = "BELONGS_TO", direction = Relationship.UNDIRECTED)
    @JsonProperty
    private Set<Student> students;

    @Relationship(type = "SEND_BY", direction = Relationship.UNDIRECTED)
    @JsonProperty
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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void belongsToStudent(Student student){
        if(this.students == null){
            this.students = new HashSet<>();
        }
        this.students.add(student);
    }

    public void sentByProvider(Provider provider){
        if (this.providers == null){
            this.providers = new HashSet<>();
        }

        if (this.providers.contains(provider)){
            return;
        }

        this.providers.add(provider);
    }

    @Override
    public String toString() {
        return "notification{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", itWasSent=" + itWasSent +
                ", isRead=" + isRead +
                ", students=" + students +
                ", providers=" + providers +
                '}';
    }
}
