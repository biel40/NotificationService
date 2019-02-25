package com.servei.notifications_service.nodes;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(name, provider.name);
    }

    @Override
    public String toString() {
        return "provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
