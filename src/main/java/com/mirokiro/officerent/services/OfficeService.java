package com.mirokiro.officerent.services;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.RentedDate;
import com.mirokiro.officerent.models.Role;
import com.mirokiro.officerent.models.User;
import com.mirokiro.officerent.repos.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    public boolean saveOffice(Office office) {
        Office officeFromDb = officeRepository.findByName(office.getName());
        if (officeFromDb != null) {
            return false;
        }
        System.out.println("saved office= "+office);
        officeRepository.save(office);
        return true;
    }
    public List<RentedDate> getRentedDatesByUser(User user) {
        List<RentedDate> rentedDatesByUser = new ArrayList<>();
        for (Office office : officeRepository.findAll()) {
            for (RentedDate rentedDate : office.getRentedDates()) {
                if (rentedDate.getUser().equals(user)) {
                    System.out.println("rentedDate= "+rentedDate.getOffice().getName());
                    rentedDatesByUser.add(rentedDate);
                }
            }
        }
        return rentedDatesByUser;
    }
}
