package com.reminder.service.resource;

import com.reminder.service.model.Reminder;
import com.reminder.service.model.Reminders;
import com.reminder.service.service.ReminderService;
import com.reminder.service.test.util.TestUtil;
import com.reminder.service.type.Status;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for ReminderResource
 */
public class ReminderResourceTest {

    @InjectMocks
    private ReminderResource reminderResource;
    @Mock
    private ReminderService reminderService;
    @Mock
    private Response response;

    @Mock
    private Reminder inputReminder;
    @Mock
    private Reminder outputReminder;
    @Mock
    private Reminders reminders;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddReminder() {
        //mock values
        when(outputReminder.getId()).thenReturn(1);
        when(reminderService.addReminder(inputReminder)).thenReturn(outputReminder);
        //make call
        Response actualResponse = reminderResource.addReminder(inputReminder);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.CREATED.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), outputReminder);
        Assert.assertEquals(((Reminder)actualResponse.getEntity()).getId(), 1);
    }

    @Test
    public void testGetAllReminders() {
        //mock values
        String dueDate = "123456789";
        String status = "DONE";
        when(reminders.getReminderList()).thenReturn(TestUtil.getReminderList());
        when(reminderService.getReminders(dueDate, status)).thenReturn(reminders);
        //make call
        Response actualResponse = reminderResource.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), reminders);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().size(), 2);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().get(0).getName(), "Travel");
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().get(1).getName(), "Shopping");
    }

    @Test
     public void testGetRemindersByDueDate() {
        //mock values
        String dueDate = "123456789";
        String status = "";
        when(reminders.getReminderList()).thenReturn(TestUtil.getReminderListByDueDate(dueDate));
        when(reminderService.getReminders(dueDate, status)).thenReturn(reminders);
        //make call
        Response actualResponse = reminderResource.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), reminders);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().size(), 1);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().get(0).getName(), "Travel");
    }

    @Test
    public void testGetRemindersByStatus() {
        //mock values
        String dueDate = "";
        String status = "DONE";
        when(reminders.getReminderList()).thenReturn(TestUtil.getReminderListByStatus(status));
        when(reminderService.getReminders(dueDate, status)).thenReturn(reminders);
        //make call
        Response actualResponse = reminderResource.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), reminders);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().size(), 1);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().get(0).getName(), "Travel");
    }

    @Test
    public void testGetRemindersByDueDateAndStatus() {
        //mock values
        String dueDate = "123456789";
        String status = "DONE";
        when(reminders.getReminderList()).thenReturn(TestUtil.getReminderListByDueDateAndStatus(dueDate, status));
        reminders.getReminderList().clear();
        when(reminderService.getReminders(dueDate, status)).thenReturn(reminders);
        //make call
        Response actualResponse = reminderResource.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), reminders);
        Assert.assertEquals(((Reminders)actualResponse.getEntity()).getReminderList().size(), 0);
    }

    @Test
    public void testGetReminder() {
        //mock values
        String id = "1";
        when(reminderService.getReminder(id)).thenReturn(outputReminder);
        when(outputReminder.getId()).thenReturn(1);
        //make call
        Response actualResponse = reminderResource.getReminder(id);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), outputReminder);
        Assert.assertEquals(((Reminder)actualResponse.getEntity()).getId(), 1);
    }

    @Test
    public void testUpdateReminder() {
        //mock values
        String id = "1";
        inputReminder.setName("Travel");
        when(outputReminder.getName()).thenReturn("Meeting");
        when(reminderService.updateReminder(inputReminder, id)).thenReturn(outputReminder);
        //make call
        Response actualResponse = reminderResource.updateReminder(inputReminder, id);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Response.Status.OK.getStatusCode());
        Assert.assertEquals(actualResponse.getEntity(), outputReminder);
        Assert.assertEquals(((Reminder)actualResponse.getEntity()).getName(), "Meeting");
    }
}
