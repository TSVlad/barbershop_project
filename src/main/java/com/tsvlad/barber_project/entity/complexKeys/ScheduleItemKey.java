package com.tsvlad.barber_project.entity.complexKeys;

import com.tsvlad.barber_project.entity.Barber;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Objects;

@Embeddable
public class ScheduleItemKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "week_day")
    private DayOfWeek weekDay;

    public ScheduleItemKey() {
    }

    public ScheduleItemKey(Barber barber, DayOfWeek weekDay) {
        this.barber = barber;
        this.weekDay = weekDay;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleItemKey that = (ScheduleItemKey) o;
        return barber.equals(that.barber) &&
                weekDay == that.weekDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barber, weekDay);
    }

    @Override
    public String toString() {
        return "ScheduleItemKey{" +
                "barber=" + barber +
                ", weekDay=" + weekDay +
                '}';
    }
}
