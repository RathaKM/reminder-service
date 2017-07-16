package com.reminder.service.exception;


import javax.ws.rs.core.Response.Status;

public enum ExceptionStatusCode {

    DATA_NOT_FOUND(Status.NOT_FOUND),
    INTERNAL_SERVICE_ERROR(Status.INTERNAL_SERVER_ERROR);

    private Status status;

    ExceptionStatusCode (Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status.toString();
    }

}
