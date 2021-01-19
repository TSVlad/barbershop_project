package com.tsvlad.barber_project.rest.controllers;

import com.tsvlad.barber_project.entity.ArchiveOrder;
import com.tsvlad.barber_project.entity.complexKeys.OrderKey;
import com.tsvlad.barber_project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/barber")
public class BarberRestController {

    @Autowired
    private DataService dataService;

    @PostMapping("/accept-order")
    public ArchiveOrder acceptOrder(@RequestBody OrderKey orderKey) {
        return dataService.acceptOrder(orderKey);
    }

    @GetMapping("/archive-orders/for-user-and-date/{username}/{dateStr}")
    public List<ArchiveOrder> getArchiveOrdersForDate(@PathVariable String dateStr, @PathVariable String username) {
        LocalDate date = LocalDate.parse(dateStr);
        return dataService.getArchiveOrdersForUserAndDate(username, date);
    }
}
