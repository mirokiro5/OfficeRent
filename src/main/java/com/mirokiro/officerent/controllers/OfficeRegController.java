package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.mirokiro.officerent.services.OfficeService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OfficeRegController {
    @Autowired
    private OfficeService officeService;
    @GetMapping("/registration/office")
    public String registration(Model model) {
        model.addAttribute("officeForm", new Office());
        model.addAttribute("title", "Регистрация");
        return "officereg";
    }
    @PostMapping("/registration/office")
    public String addOffice(@ModelAttribute("officeForm") Office officeForm, BindingResult bindingResult, Model model){
        //userForm.setId(1);
        System.out.println("userForm= "+officeForm);
        if (!officeService.saveOffice(officeForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "officereg";
        }
        return "redirect:/";
    }
}
