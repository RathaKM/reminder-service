package com.reminder.service.test.util;

import com.reminder.service.model.Reminder;
import com.reminder.service.model.ResourceLink;
import com.reminder.service.type.ReminderStatus;
import com.reminder.service.util.HateoasLinkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Test
 */
public class TestUtil {

    private final static String CONTEXT_URI = "http://localhost:8080/reminder-service/v1/";
    private final static String RESOURCE_URI = "reminders";

    public static List<Reminder> getReminderListByDueDate(String dueDate) {
        List<Reminder> listOutputReminder = new ArrayList<>();
        getReminderList().stream()
                .filter(reminder -> reminder.getDueDate() == Integer.parseInt(dueDate))
                .forEach(reminder ->  listOutputReminder.add(reminder));
        return listOutputReminder;
    }

    public static List<Reminder> getReminderListByStatus(String status) {
        List<Reminder> listOutputReminder = new ArrayList<>();
        getReminderList().stream()
                .filter(reminder -> reminder.getStatus().toString().equals(status))
                .forEach(reminder ->  listOutputReminder.add(reminder));
        return listOutputReminder;
    }

    public static List<Reminder> getReminderListByDueDateAndStatus(String dueDate, String status) {
        List<Reminder> listOutputReminder = new ArrayList<>();
        getReminderList().stream()
                .filter(reminder -> reminder.getDueDate() == Long.parseLong(dueDate))
                .filter(reminder -> reminder.getStatus().toString().equals(status))
                .forEach(reminder -> listOutputReminder.add(reminder));
        return listOutputReminder;
    }

    public static List<Reminder> getReminderById(String id) {
        List<Reminder> listOutputReminder = new ArrayList<>();
        getReminderList().stream()
                .filter(reminder -> reminder.getId() == Integer.parseInt(id))
                .forEach(reminder -> listOutputReminder.add(reminder));
        return listOutputReminder;
    }

    public static List<Reminder> getReminderList() {
        Reminder reminder1 = new Reminder();
        Reminder reminder2 =  new Reminder();
        List<Reminder> listReminder = new ArrayList<>();
        reminder1.setId(1);
        reminder1.setName("Travel");
        reminder1.setDueDate(123456789);
        reminder1.setStatus(ReminderStatus.DONE);
        reminder1.setLinks(HateoasLinkUtil.getHateoasLinkForSearch(RESOURCE_URI, "/1", ""));
        listReminder.add(reminder1);
        reminder2.setId(2);
        reminder2.setName("Shopping");
        reminder2.setDueDate(987654321L);
        reminder2.setStatus(ReminderStatus.NOT_DONE);
        List<ResourceLink> listResourceLink2 = HateoasLinkUtil.getHateoasLinkForSearch(RESOURCE_URI, "/2", "");
        listReminder.add(reminder2);
        return listReminder;
    }


}
