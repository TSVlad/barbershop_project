package com.tsvlad.barber_project.rest.errors;

public class ErrorResponse {
    private int status;
    private String message;
    private long time;

    public ErrorResponse(int status, String message, long time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public ErrorResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
