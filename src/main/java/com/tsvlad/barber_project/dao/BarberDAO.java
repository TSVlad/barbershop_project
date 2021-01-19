package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Barber;

import java.time.DayOfWeek;
import java.util.List;

public interface BarberDAO {
    List<Barber> getBarbers();
    List<Barber> getArchivedBarbers();
    Barber getBarber(int id);
    Barber getBarberByUsername(String username);
    Barber saveBarber(Barber barber);
}
