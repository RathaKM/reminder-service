package com.reminder.service.service;

import com.reminder.service.model.Reminder;
import com.reminder.service.model.Reminders;

/**
 * This class provides service for the Reminder resource.
 */

public interface ReminderService {
    public Reminder addReminder(Reminder reminder);
    public Reminders getReminders(String dueDate, String status);
    public Reminder getReminder(String id);
    public Reminder updateReminder(Reminder reminder, String id);
}
