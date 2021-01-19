package com.tsvlad.barber_project.rest.controllers;

import com.tsvlad.barber_project.entity.ArchiveOrder;
import com.tsvlad.barber_project.entity.Barber;
import com.tsvlad.barber_project.entity.Service;
import com.tsvlad.barber_project.entity.complexKeys.OrderKey;
import com.tsvlad.barber_project.json_classes.BarberInfoJSON;
import com.tsvlad.barber_project.rest.errors.exceptions.DbDataNotFoundException;
import com.tsvlad.barber_project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private DataService dataService;

    @DeleteMapping("/service/delete/{id}")
    public Service deleteService(@PathVariable int id) {
        Service service = dataService.getService(id);

        if (service == null) {
            throw new DbDataNotFoundException("Not found service with id = " + id);
        }

        dataService.deleteService(id);
        return service;
    }

    @PostMapping("/service/save")
    public Service saveService(@RequestBody Service service) {
        dataService.saveService(service);
        return service;
    }

    @PostMapping("/cancel-order")
    public ArchiveOrder cancelOrder(@RequestBody OrderKey orderKey) {
        return dataService.cancelOrder(orderKey);
    }

    @GetMapping("/archive-orders/for-date/{dateStr}")
    public List<ArchiveOrder> getArchiveOrderForDate(@PathVariable String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        return dataService.getArchiveOrdersForDate(date);
    }

    @DeleteMapping("/active-workers/{id}")
    public Barber deleteBarber(@PathVariable int id) {
        Barber barber = dataService.getBarber(id);

        if (barber == null) {
            throw new DbDataNotFoundException("Not found barber with id = " + id);
        }

        dataService.setBarberActivate(barber, false);
        return barber;
    }

    @PostMapping("/active-workers/save")
    public Barber saveBarber(@RequestBody Barber barber) {
        dataService.saveBarber(barber);
        return barber;
    }

    @DeleteMapping("/archived-workers/{id}")
    public Barber activateBarber(@PathVariable int id){
        Barber barber = dataService.getBarber(id);

        if (barber == null) {
            throw new DbDataNotFoundException("Not found barber with id = " + id);
        }

        dataService.setBarberActivate(barber, true);
        return barber;
    }
}
