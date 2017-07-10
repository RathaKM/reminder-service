package com.reminder.service.service;

import com.reminder.service.dao.ReminderDAO;
import com.reminder.service.model.Reminder;
import com.reminder.service.model.Reminders;
import com.reminder.service.type.RelValue;
import com.reminder.service.util.HateoasLinkUtil;

import java.util.logging.Logger;

/**
 * This class is a Service implementation
 */

public class ReminderServiceImpl implements ReminderService {

    private static final String RESOURCE_URI = "reminders";
    private final Logger logger = Logger.getLogger("ReminderServiceImpl");
    private ReminderDAO reminderDAO; // can be injected

    public ReminderServiceImpl() {
        reminderDAO = new ReminderDAO();
    }

    /**
     * Saves the created Reminder instance and updates the HATEOAS link for the resource
     * @param reminder the Reminder instance to be saved
     * @return the created Reminder instance
     */
    @Override
    public Reminder addReminder(Reminder reminder) {
        reminderDAO.insert(reminder);
        String param = "/" + reminder.getId();
        reminder.setLinks(HateoasLinkUtil.getHateoasLink(RelValue.CREATE, RESOURCE_URI, param, ""));
        logger.info("Created Reminder:" + reminder);
        return reminder;
    }

    /**
     * Fetches a list of Reminder instance based on the given search fields, dueDate and/or status
     * and updates the HATEOAS link for the resource
     * @param dueDate the dueDate field used for search
     * @param status the status field used for search
     * @return the fetched Reminder instance
     */
    @Override
    public Reminders getReminders(String dueDate, String status) {
        Reminders reminders = new Reminders();
        final String filter = getFilterString(reminders, dueDate, status);
        reminders.getReminderList().stream()
                .forEach(reminder -> reminder.setLinks(HateoasLinkUtil.getHateoasLink(RelValue.SEARCH, RESOURCE_URI, "/" + reminder.getId(), filter)));
        logger.info("Fetched All Reminders for filter:" + filter);
        return reminders;
    }

    /**
     * Fetches a Reminder instance based on the given Id and updates the HATEOAS link for the resource
     * @param id the primary key field used for search
     * @return the fetched Reminder instance
     */
    @Override
    public Reminder getReminder(String id) {
        Reminder reminder = reminderDAO.selectById(id);
        reminder.setLinks(HateoasLinkUtil.getHateoasLink(RelValue.GET, RESOURCE_URI, "/" + id, ""));
        logger.info("Fetched Reminder:" + reminder);
        return reminder;
    }

    /**
     * Updates a Reminder instance based on the given Id and updates the HATEOAS link for the resource
     * @param reminder the Reminder instance to be updated
     * @param id the primary key field
     * @return the updated Reminder instance
     */
    @Override
    public Reminder updateReminder(Reminder reminder, String id) {
        Reminder existingReminder = reminderDAO.selectById(id);
        existingReminder.setName(reminder.getName());
        existingReminder.setDescription(reminder.getDescription());
        existingReminder.setDueDate(reminder.getDueDate());
        existingReminder.setStatus(reminder.getStatus());
        reminderDAO.update(existingReminder);
        existingReminder.setLinks(HateoasLinkUtil.getHateoasLink(RelValue.UPDATE, RESOURCE_URI, "/" + id, ""));
        logger.info("Reminder updated:" + reminder);
        return existingReminder;
    }

    /**
     * Filters the Reminder instances based on the filter fields.
     * @param reminders the list of Reminder instance filtered based on the filter fields
     * @param dueDate the filter field dueDate
     * @param status the filter field status
     * @return returns the final filter string
     */
    private String getFilterString(Reminders reminders, String dueDate, String status) {
        String filter = "";
        if(isSearchByDueDateAndStatus(dueDate, status)) {
            reminders.setReminderList(reminderDAO.selectByDueDateAndStatus(dueDate, status));
            filter = getFilterStringForDueDateAndStatus(dueDate, status);
        } else if(isSearchAll(dueDate, status)) {
            reminders.setReminderList(reminderDAO.selectAll());
        } else {
            logger.info("filter:" + filter);
            filter = getFilterStringForDueDateOrStatus(dueDate, status);
            reminders.setReminderList(reminderDAO.selectByDueDateOrStatus(dueDate, status));
        }
        return filter;
    }

    /**
     * Checks if the search is to be done by DueDate and Status
     * @param dueDate the filter field dueDate
     * @param status the filter field status
     * @return returns the isSearchByDueDateAndStatus result
     */
    private boolean isSearchByDueDateAndStatus(String dueDate, String status){
        boolean isSearchByDueDateAndStatus = dueDate != null && status != null && !dueDate.trim().isEmpty() && !status.trim().isEmpty();
        logger.info("isSearchByDueDateAndStatus:" + isSearchByDueDateAndStatus);
        return isSearchByDueDateAndStatus;
    }

    /**
     * Checks if the search is to done for all
     * @param dueDate the filter field dueDate
     * @param status the filter field status
     * @return returns the isSearchByDueDateAndStatus result
     */
    private boolean isSearchAll(String dueDate, String status){
        boolean isSearchAll = (dueDate == null || dueDate.trim().isEmpty()) && (status == null || status.trim().isEmpty());
        logger.info("isSearchAll:" + isSearchAll);
        return isSearchAll;
    }

    /**
     * Gets the filter string for DueDate And Status
     * @param dueDate the filter field dueDate
     * @param status the filter field status
     * @return returns the filter string
     */
    private String getFilterStringForDueDateAndStatus(String dueDate, String status) {
        String filter = "?dueDate=" + dueDate + "&status=" + status;
        logger.info("filter:" + filter);
        return filter;
    }

    /**
     * Gets the filter string for DueDate Or Status
     * @param dueDate the filter field dueDate
     * @param status the filter field status
     * @return returns the filter string
     */
    private String getFilterStringForDueDateOrStatus(String dueDate, String status) {
        String paramName = dueDate != null && !dueDate.trim().isEmpty()? "dueDate" : "status";
        String paramValue = dueDate != null && !dueDate.trim().isEmpty()? dueDate : status;
        String filter = "?" + paramName + "=" + paramValue;
        logger.info("filter:" + filter);
        return filter;
    }
}

