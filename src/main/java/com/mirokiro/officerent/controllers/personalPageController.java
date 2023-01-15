package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.RentedDate;
import com.mirokiro.officerent.models.User;
import com.mirokiro.officerent.repos.OfficeRepository;
import com.mirokiro.officerent.repos.RentedDateRepository;
import com.mirokiro.officerent.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class personalPageController {
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentedDateRepository rentedDateRepository;
    @GetMapping("/personal")
    public String personalPage(Model model, Authentication authentication){
        Iterable<Office> offices = officeRepository.findAll();
        Iterable<RentedDate> rentedDates = rentedDateRepository.findAll();
        model.addAttribute("offices", offices);
        model.addAttribute("rentedDates", rentedDates);
        User user = userRepository.findByUsername(authentication.getName());
        System.out.println(authentication.getName());
        model.addAttribute("user", user);
        return "personal";
    }

}
