package com.reminder.service.resource;

import com.reminder.service.exception.ApplicationException;
import com.reminder.service.model.Reminder;
import com.reminder.service.model.Reminders;
import com.reminder.service.service.ReminderService;
import com.reminder.service.service.ReminderServiceImpl;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * This class is a Resource class
 */

@Path("/v1")
public class ReminderResource {

    private final Logger logger = Logger.getLogger("ReminderResource");
    @Inject
    private ReminderService reminderService;

    /**
     * Creates a Reminder instance and saves it in the DB
     * @param reminder the Reminder instance to be saved
     * @return the created and saved Reminder instance
     */
    @POST
    @Path("/reminders")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response addReminder(@Valid Reminder reminder) {
        logger.info("Reminder:" + reminder);
        Reminder newReminder = reminderService.addReminder(reminder);
        return Response.status(201).entity(newReminder).build();
    }

    /**
     * Fetches a list of Reminder instance based on the given search fields, dueDate and/or status
     * @param dueDate the dueDate field used for search
     * @param status the status field used for search
     * @return the fetched Reminder instance
     */
    @GET
    @Path("/reminders")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getReminders(@QueryParam("dueDate") String dueDate, @QueryParam("status") String status) throws ApplicationException {
        logger.info("dueDate:" + dueDate + " and status:" + status);
        Reminders reminders = reminderService.getReminders(dueDate, status);
        return Response.status(200).entity(reminders).build();
    }

    /**
     * Fetches a Reminder instance based on the given Id
     * @param id the primary key field used for search
     * @return the fetched Reminder instance
     */
    @GET
    @Path("/reminders/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response getReminder(@PathParam("id") @Pattern(message = "{reminder.id.invalid}", regexp = "^\\d+$")String id) throws ApplicationException {
        logger.info("id:" + id);
        Reminder reminder = reminderService.getReminder(id);
        return Response.status(200).entity(reminder).build();
    }

    /**
     * Updates a Reminder instance based on the given Id
     * @param reminder the Reminder instance to be updated
     * @param id the primary key field
     * @return the updated Reminder instance
     */
    @PUT
    @Path("/reminders/{id}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response updateReminder(@Valid Reminder reminder, @PathParam("id") @Pattern(message = "{reminder.id.invalid}", regexp = "^\\d+$") String id) throws ApplicationException {
        logger.info("Reminder for Update:" + reminder);
        Reminder updatedReminder = reminderService.updateReminder(reminder, id);
        return Response.status(200).entity(updatedReminder).build();
    }

    /**
     * Fetches a Reminder instance based on the given Id
     * @param id the primary key field used for search
     * @return the fetched Reminder instance
     */
    @DELETE
    @Path("/reminders/{id}")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response deleteReminder(@PathParam("id") @Pattern(message = "{reminder.id.invalid}", regexp = "^\\d+$") String id) throws ApplicationException {
        logger.info("id:" + id);
        reminderService.deleteReminder(id);
        return Response.status(204).build();
    }
}
