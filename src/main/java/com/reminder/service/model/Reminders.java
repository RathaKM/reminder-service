package com.reminder.service.model;

import java.util.List;


/**
 * This class returns the list of Reminder instances
 */

public class Reminders {

    private List<Reminder> reminderList;

    public List<Reminder> getReminderList() {
        return reminderList;
    }

    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }

}
