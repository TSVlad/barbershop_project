package com.tsvlad.barber_project.controller;

import com.tsvlad.barber_project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("barber")
public class BarberController {

    @Autowired
    private DataService dataService;

    @GetMapping("active-orders")
    public String getActiveOrders(Model model, Principal principal) {
        model.addAttribute("activeOrders", dataService.getActiveOrdersForUser(principal.getName()));
        return "barber-active-orders";
    }

    @GetMapping("archive-orders")
    public String getArchiveOrders(Model model, Principal principal) {
        LocalDate initialDate = LocalDate.now().minus(1, ChronoUnit.DAYS);
        model.addAttribute("archiveOrders", dataService.getArchiveOrdersForUserAndDate(principal.getName(), initialDate));
        model.addAttribute("initialDate", initialDate);
        model.addAttribute("username", principal.getName());
        return "barber-archive-orders";
    }
}
