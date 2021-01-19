package com.tsvlad.barber_project.rest.controllers;

import com.tsvlad.barber_project.entity.ActiveOrder;
import com.tsvlad.barber_project.entity.Barber;
import com.tsvlad.barber_project.json_classes.ActiveOrderJSON;
import com.tsvlad.barber_project.rest.errors.exceptions.IncorrectDateException;
import com.tsvlad.barber_project.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/main")
public class MainRestController {
    @Autowired
    DataService dataService;

    @GetMapping("/get-barbers/for-day/{day}")
    public List<Barber> getBarbersForWeekDay(@PathVariable String day) {
        LocalDate date = LocalDate.parse(day);
        return dataService.getBarbersWhichWorkAtDay(date.getDayOfWeek());
    }

    @GetMapping("/get-barber-hours/{barber}/{date}")
    public List<Integer> getBarberHoursForDate(@PathVariable int barber, @PathVariable String date) {
        return dataService.getAvailableWorkingHours(barber, date);
    }

    @PostMapping("/order/new")
    public ActiveOrder createOrder(@RequestBody ActiveOrderJSON activeOrderJSON) {
        if (activeOrderJSON.getDate().isBefore(LocalDate.now()) || (activeOrderJSON.getDate().isEqual(LocalDate.now()) && LocalDateTime.now().getHour() >= activeOrderJSON.getHour())) {
            throw new IncorrectDateException("Дата время записи некорректны");
        }
        return dataService.createOrder(activeOrderJSON);
    }
}
