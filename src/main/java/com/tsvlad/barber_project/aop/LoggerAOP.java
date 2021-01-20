package com.tsvlad.barber_project.aop;

public class LoggerAOP {

    public void logException(Exception ex) {
        System.out.println(ex);
    }
}
