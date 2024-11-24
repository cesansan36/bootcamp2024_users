package com.pragmabootcamp.user.domain.secondaryport;

import com.pragmabootcamp.user.domain.model.Role;

public interface IRolePersistencePort {

    Role getRole(String name);
}
