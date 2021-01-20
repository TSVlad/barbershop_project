package com.tsvlad.barber_project.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class LoggerAOP {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logException(Exception ex) {
        logger.error(LocalDateTime.now() + " Exception caught: " + ex);
    }
}
