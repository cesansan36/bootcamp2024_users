package com.pragmabootcamp.user.domain.model;

import com.pragmabootcamp.user.domain.exception.EmptyFieldException;
import com.pragmabootcamp.user.domain.util.DomainConstants;

public class Role {
    private final Integer id;
    private final String name;

    public Role(Integer id, String name) {
        validate(id, name);
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void validate(Integer id, String name) {

        if (id == null ) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.RoleField.ID));
        }
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyFieldException(DomainConstants.FIELD_EMPTY_MESSAGE.formatted(DomainConstants.RoleField.NAME));
        }
    }
}
