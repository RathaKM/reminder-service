package com.reminder.service.exception;

import javax.ws.rs.core.Response.Status;

/**
 * This Class helps to map application exception
 *
 */
public class ApplicationException extends Exception {

    private Status status;
    private String debugId;
    private String link;

    public ApplicationException(Status status, String debugId, String message, String link) {
        super(message);
        this.status = status;
        this.debugId = debugId;
        this.link = link;
    }

    public ApplicationException() { }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDebugId() {
        return debugId;
    }

    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
