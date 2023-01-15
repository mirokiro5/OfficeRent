package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.User;
import com.mirokiro.officerent.repos.OfficeRepository;
import com.mirokiro.officerent.repos.RoleRepository;
import com.mirokiro.officerent.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
@Controller
public class OfficeAddToUserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OfficeRepository officeRepository;
    @GetMapping("/add-office{id}")
    public String addOfficeForUser(Model model, @RequestParam(value = "id") long id) {
        User user = userRepository.findById(id);
        Iterable<Office> offices = officeRepository.findAll();
        model.addAttribute("offices", offices);
        model.addAttribute("user", user);
        return "add-office";
    }
    @PostMapping("/add-office{id}")
    public String addOfficeForUserPost(Model model, @RequestParam(value = "id") long id, @RequestParam String office) {
        User user = userRepository.findById(id);
        Office newOffice = officeRepository.findByName(office);
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
        userRepository.save(user);
        return "redirect:/";
    }
}
