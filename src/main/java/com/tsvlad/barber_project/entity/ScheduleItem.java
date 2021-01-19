package com.tsvlad.barber_project.entity;


import com.tsvlad.barber_project.entity.complexKeys.ScheduleItemKey;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "schedule_item")
public class ScheduleItem {

    @EmbeddedId
    private ScheduleItemKey scheduleItemKey;

    @Column(name = "work_start")
    private int workStart;

    @Column(name = "work_end")
    private int workEnd;

    public ScheduleItem() {
    }

    public ScheduleItem(ScheduleItemKey scheduleItemKey, int workStart, int workEnd) {
        this.scheduleItemKey = scheduleItemKey;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public ScheduleItemKey getScheduleItemKey() {
        return scheduleItemKey;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "scheduleItemKey=" + scheduleItemKey +
                ", workStart=" + workStart +
                ", workEnd=" + workEnd +
                '}';
    }

    public void setScheduleItemKey(ScheduleItemKey scheduleItemKey) {
        this.scheduleItemKey = scheduleItemKey;
    }

    public int getWorkStart() {
        return workStart;
    }

    public void setWorkStart(int workStart) {
        this.workStart = workStart;
    }

    public int getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(int workEnd) {
        this.workEnd = workEnd;
    }
}
