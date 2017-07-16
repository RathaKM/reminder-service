package com.reminder.service.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {

    @Override
    public Response toResponse(ApplicationException e) {
        return Response
                .status(e.getStatus())
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErrorMessage(e))
                .build();
    }
}