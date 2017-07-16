package com.reminder.service.exception;


import org.glassfish.jersey.server.ResourceConfig;

public class ReminderApplication extends ResourceConfig {

    public ReminderApplication() {
		register(ApplicationExceptionMapper.class);
    }
}
