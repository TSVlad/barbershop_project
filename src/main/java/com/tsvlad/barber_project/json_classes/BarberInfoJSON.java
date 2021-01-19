package com.tsvlad.barber_project.json_classes;

import com.tsvlad.barber_project.entity.Barber;

public class BarberInfoJSON {
    private Barber barber;
    private String password;

    public BarberInfoJSON() {
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
