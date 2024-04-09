package com.pragmabootcamp.user.domain.model;

public class Role {
    private final Integer id;
    private final String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
