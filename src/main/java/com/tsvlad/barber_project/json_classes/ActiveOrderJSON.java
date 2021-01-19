package com.tsvlad.barber_project.json_classes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;

public class ActiveOrderJSON {
    private String email;
    private int barberId;
    private int serviceId;


    private LocalDate date;

    private int hour;
    private String comment;

    public ActiveOrderJSON() {
    }

    public ActiveOrderJSON(String email, int barberId, int serviceId, LocalDate date, int hour, String comment) {
        this.email = email;
        this.barberId = barberId;
        this.serviceId = serviceId;
        this.date = date;
        this.hour = hour;
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ActiveOrderJSON{" +
                "email='" + email + '\'' +
                ", barberId=" + barberId +
                ", serviceId=" + serviceId +
                ", date=" + date +
                ", hour=" + hour +
                ", comment='" + comment + '\'' +
                '}';
    }
}
