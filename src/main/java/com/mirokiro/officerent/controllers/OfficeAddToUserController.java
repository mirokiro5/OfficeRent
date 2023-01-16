package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.RentedDate;
import com.mirokiro.officerent.models.User;
import com.mirokiro.officerent.repos.OfficeRepository;
import com.mirokiro.officerent.repos.RentedDateRepository;
import com.mirokiro.officerent.repos.RoleRepository;
import com.mirokiro.officerent.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Objects;
@Controller
public class OfficeAddToUserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    RentedDateRepository rentedDateRepository;
    @GetMapping("/add-office")
    public String addOfficeForUser(Model model, Authentication authentication, @RequestParam(value = "office_id") long office_id) {
        User user = userRepository.findByUsername(authentication.getName());
        Office office = officeRepository.findById(office_id);
        model.addAttribute("office", office);
        model.addAttribute("user", user);
        return "add-office";
    }
    @PostMapping("/add-office")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    public String addOfficeForUserPost(Model model, Authentication authentication, @RequestParam(value = "office_id") long office_id,
                                       @RequestParam(value = "startDate", defaultValue = "1800-01-01", required = false)  @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                       @RequestParam(value = "endDate", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
        User user = userRepository.findByUsername(authentication.getName());
        Office newOffice = officeRepository.findById(office_id);;
        for (Office o : user.getOffices()) {
            if (Objects.equals(o.getName(), newOffice.getName())) {
                Iterable<Office> offices = officeRepository.findAll();
                model.addAttribute("offices", offices);
                model.addAttribute("user", user);
                model.addAttribute("error", "Такой офис у пользователя уже есть");
                return "add-office";
            }
        }
        user.getOffices().add(newOffice);
        user.setRentedOffices(user.getRentedOffices() + 1);
        user.setTotalPrice(user.getTotalPrice().add(newOffice.getPrice()));
        RentedDate rentedDate = new RentedDate();
        rentedDate.setStartDate(startDate);
        rentedDate.setEndDate(endDate);
//        rentedDate.setOffice(newOffice);
        rentedDate.setUser(user);
        newOffice.setRentedDate(rentedDate);
        System.out.println("------------------Rent------------------");
        System.out.println("rentedDate= "+rentedDate.getId()+" "+rentedDate.getStartDate()+" "+rentedDate.getEndDate()+" "+rentedDate.getUser().getUsername());
        System.out.println("user= "+user);
        rentedDateRepository.save(rentedDate);
        officeRepository.save(newOffice);
        userRepository.save(user);
        return "redirect:/personal";
    }
}
