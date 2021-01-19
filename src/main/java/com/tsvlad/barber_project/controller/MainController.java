package com.tsvlad.barber_project.controller;

import com.tsvlad.barber_project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("services", dataService.getServices());
        model.addAttribute("barbers", dataService.getBarbers());
        return "main";
    }

    @GetMapping("/403")
    public String getForbiddenPage() {
        return "forbidden";
    }
}
