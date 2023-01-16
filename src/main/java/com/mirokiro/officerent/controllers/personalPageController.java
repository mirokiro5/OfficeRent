package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.RentedDate;
import com.mirokiro.officerent.models.User;
import com.mirokiro.officerent.repos.OfficeRepository;
import com.mirokiro.officerent.repos.RentedDateRepository;
import com.mirokiro.officerent.repos.UserRepository;
import com.mirokiro.officerent.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class personalPageController {
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentedDateRepository rentedDateRepository;
    @Autowired
    private OfficeService officeService;
    @GetMapping("/personal")
    public String personalPage(Model model, Authentication authentication){
//        Iterable<Office> offices = officeRepository.findAll();
//        Iterable<RentedDate> rentedDates = rentedDateRepository.findAll();
//        model.addAttribute("offices", offices);
//        model.addAttribute("rentedDates", rentedDates);
        User user = userRepository.findByUsername(authentication.getName());
        List<RentedDate> rentedDatesByUser = officeService.getRentedDatesByUser(user);
        System.out.println(authentication.getName());
        System.out.println("user= "+user);
        for(RentedDate r: rentedDatesByUser){
            System.out.println(r.getOffice().getName());
            System.out.println(r.getStartDate());
            System.out.println(r.getEndDate());
        }
        model.addAttribute("rentedDatesByUser", rentedDatesByUser);
        model.addAttribute("user", user);
        return "personal";
    }

}
