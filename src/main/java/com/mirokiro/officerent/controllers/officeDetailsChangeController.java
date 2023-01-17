package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.repos.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
@Controller
public class officeDetailsChangeController {
    @Autowired
    OfficeRepository officeRepository;
    @GetMapping("/officeDetails/change")
    public String admin(Model model) {
        List<Office> office = officeRepository.findAll();
        model.addAttribute("officeChangeForm", new Office());
        model.addAttribute("office", office);
        return "officeDetailsChange";
    }
    @PostMapping("/officeDetails/change")
    public String addRoleForUserPost(Model model, @RequestParam(value = "id") long id, @ModelAttribute("officeChangeForm") Office officeChangeForm) {
        Office office = officeRepository.findById(id);
        office.setName(officeChangeForm.getName());
        office.setAddress(officeChangeForm.getAddress());
        office.setDescription(officeChangeForm.getDescription());
        office.setPrice(officeChangeForm.getPrice());
        office.setImage(officeChangeForm.getImage());
        officeRepository.save(office);
        return "redirect:/officeDetails/change";
    }
}
