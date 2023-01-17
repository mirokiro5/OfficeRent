package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.models.Role;
import com.mirokiro.officerent.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.mirokiro.officerent.repos.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        Iterable<User> users = userRepository.findAll();
        Iterable<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "admin";
    }
    @PostMapping("/admin")
    public String addRoleForUserPost(Model model, @RequestParam(value = "id") long id, @RequestParam String role) {
        User user = userRepository.findById(id);
        Role newRole = roleRepository.findByName(role);
        for (Role r : user.getRoles()) {
            if (Objects.equals(r.name, newRole.name)) {
                Iterable<Role> roles = roleRepository.findAll();
                model.addAttribute("roles", roles);
                model.addAttribute("user", user);
                model.addAttribute("error", "Такая роль у пользователя уже есть");
                return "admin";
            }
        }
        user.getRoles().set(0, newRole);
        userRepository.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }
}
