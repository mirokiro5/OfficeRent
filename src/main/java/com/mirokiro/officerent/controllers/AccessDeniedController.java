package com.mirokiro.officerent.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AccessDeniedController{
    @GetMapping("/accessDenied")
    public String AccessDenied() {
        return "accessDenied";
    }

}
