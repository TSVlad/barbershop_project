package com.tsvlad.barber_project.entity.complexKeys;

import com.tsvlad.barber_project.entity.Barber;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class OrderKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "order_hour")
    private int hour;

    public OrderKey() {
    }

    public OrderKey(Barber barber, LocalDate date, int hour) {
        this.barber = barber;
        this.date = date;
        this.hour = hour;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderKey orderKey = (OrderKey) o;
        return hour == orderKey.hour &&
                barber.equals(orderKey.barber) &&
                date.equals(orderKey.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barber, date, hour);
    }

    @Override
    public String toString() {
        return "OrderKey{" +
                "barber=" + barber +
                ", date=" + date +
                ", hour=" + hour +
                '}';
    }
}
