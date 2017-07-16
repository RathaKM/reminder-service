package com.reminder.service.exception;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Error message class
 */

@XmlRootElement
@XmlType(propOrder={"status", "message", "debugId", "link"})
public class ErrorMessage {

    // contains the HTTP Status code returned by the server
    private Status status;
    // application debug code
    private String debugId;
    // message describing the error
    private String message;
    // link point to documentation page
    private String link;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ErrorMessage(ApplicationException ex){
        this.status = ex.getStatus();
        this.debugId = ex.getDebugId();
        this.message = ex.getMessage();
        this.link = ex.getLink();
    }

    public ErrorMessage() {}
}
