package com.reminder.service.service;

import com.reminder.service.exception.ApplicationException;
import com.reminder.service.model.Reminder;
import com.reminder.service.model.Reminders;

/**
 * This class provides service for the Reminder resource.
 */

public interface ReminderService {
    public Reminder addReminder(Reminder reminder);
    public Reminders getReminders(String dueDate, String status) throws ApplicationException;
    public Reminder getReminder(String id) throws ApplicationException;
    public Reminder updateReminder(Reminder reminder, String id) throws ApplicationException;
    public void deleteReminder(String id) throws ApplicationException;
}
