package com.mirokiro.officerent.repos;

import com.mirokiro.officerent.models.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {
    List<Office> findAll();
    Office findByName(String name);
    Office findById(long id);
}
