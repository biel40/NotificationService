package com.servei.notifications_service.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by tanin on 20/02/2019.
 */

@NodeEntity
public class Provider {
    @Id @GeneratedValue private Long id;
    private String name;

    public Provider(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
