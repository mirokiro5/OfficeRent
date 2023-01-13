package com.mirokiro.officerent.repos;

import com.mirokiro.officerent.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}