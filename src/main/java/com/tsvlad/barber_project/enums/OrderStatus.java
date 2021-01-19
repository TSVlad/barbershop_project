package com.tsvlad.barber_project.enums;

public enum OrderStatus {
    CANCEL("Отменен"),

    ACCEPT("Принят");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
