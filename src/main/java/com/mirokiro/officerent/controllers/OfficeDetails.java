package com.mirokiro.officerent.controllers;

import com.mirokiro.officerent.models.Office;
import com.mirokiro.officerent.repos.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OfficeDetails {
    @Autowired
    private OfficeRepository officeRepository;
    @GetMapping("/officeDetails")
    public String officeDetail(Model model, @RequestParam(value = "office_id") long officeId) {
        Office officeById = officeRepository.findById(officeId);
        model.addAttribute("officeById", officeById);
        return "officeDetails";
    }
}
