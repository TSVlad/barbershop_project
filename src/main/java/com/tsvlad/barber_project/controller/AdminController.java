package com.tsvlad.barber_project.controller;

import com.tsvlad.barber_project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private DataService dataService;

    @GetMapping("/edit-services")
    public String getEditServicesPage(Model model) {
        model.addAttribute("services", dataService.getServices());
        return "admin-services-edit";
    }

    @GetMapping("/active-orders")
    public String getActiveOrdersPage(Model model, Principal principal) {
        model.addAttribute("activeOrders", dataService.getActiveOrders());
        return "admin-active-orders";
    }

    @GetMapping("/archive-orders")
    public String getArchiveOrdersPage(Model model) {
        LocalDate date = LocalDate.now();
        model.addAttribute("archiveOrders", dataService.getArchiveOrdersForDate(date));
        model.addAttribute("initialDate", date);
        return "admin-archive-orders";
    }

    @GetMapping("/active-workers")
    public String getActiveWorkers(Model model) {
        model.addAttribute("barbers", dataService.getBarbers());
        return "admin-active-workers";
    }

    @GetMapping("/archived-workers")
    public String getArchivedWorkers(Model model) {
        model.addAttribute("barbers", dataService.getArchivedBarbers());
        return "admin-archived-workers";
    }
}
