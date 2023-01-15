package com.mirokiro.officerent.repos;

import com.mirokiro.officerent.models.RentedDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentedDateRepository extends CrudRepository<RentedDate, Long> {
    List<RentedDate> findAll();
    RentedDate findById(long id);
}
