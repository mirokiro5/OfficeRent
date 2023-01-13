package com.mirokiro.officerent.repos;

import com.mirokiro.officerent.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findById(long id);
}
