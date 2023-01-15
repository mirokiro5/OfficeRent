package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.repos.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    OfficeRepository officeRepository;
    @GetMapping("/")
    public String home(Model model){
        Iterable<Office> offices = officeRepository.findAll();
        model.addAttribute("offices", offices);
        return "home";
    }
    @GetMapping("/login")
    public String login() {
        return "login2";
    }
//    @PostMapping("/login")
//    public String loginPost() {
//        System.out.println("login");
//        return "redirect:/";
//    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
