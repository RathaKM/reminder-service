package com.reminder.service.service;

import com.reminder.service.dao.ReminderDAO;
import com.reminder.service.model.Reminder;
import com.reminder.service.model.Reminders;
import com.reminder.service.model.ResourceLink;
import com.reminder.service.resource.ReminderResource;
import com.reminder.service.test.util.TestUtil;
import com.reminder.service.type.HttpMethod;
import com.reminder.service.type.RelValue;
import com.reminder.service.type.Status;
import com.reminder.service.util.HateoasLinkUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test class for ReminderService
 */
public class ReminderServiceTest {

    @InjectMocks
    private ReminderServiceImpl reminderService;

    @Mock
    private ReminderDAO reminderDAO;
    @Mock
    private Reminder inputReminder;
    @Mock
    private Reminder outputReminder;
    @Mock
    private Reminder outputReminder2;
    @Mock
    private Reminders reminders;

    private final static String CONTEXT_URI = "http://localhost:8080/reminder-service/v1/";
    private final static String RESOURCE_URI = "reminders";

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddReminder() {
        //mock values
        when(inputReminder.getStatus()).thenReturn(Status.DONE);
        List<ResourceLink> listResourceLink = HateoasLinkUtil.getHateoasLinkForAdd(RESOURCE_URI, "/1");
        when(inputReminder.getLinks()).thenReturn(listResourceLink);
        //make call
        Reminder actualResponse = reminderService.addReminder(inputReminder);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Status.DONE);
        Assert.assertEquals(actualResponse.getLinks().size(), 3);
        Assert.assertEquals(actualResponse.getLinks().get(0).getRel(), RelValue.SELF.toString());
        Assert.assertEquals(actualResponse.getLinks().get(1).getHref(), CONTEXT_URI + RESOURCE_URI + "/1");
        Assert.assertEquals(actualResponse.getLinks().get(2).getMethod(), HttpMethod.PUT.toString());
    }

    @Test
    public void testGetAllReminders() {
        //mock values
        String dueDate = "";
        String status = null;
        when(reminderDAO.selectAll()).thenReturn(TestUtil.getReminderList());
        //make call
        Reminders actualResponse = reminderService.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getReminderList().size(), 2);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getStatus(), Status.DONE);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().size(), 2);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(0).getRel(), RelValue.SELF.toString());
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(1).getHref(), CONTEXT_URI + RESOURCE_URI + "/1");
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(1).getMethod(), HttpMethod.PUT.toString());
        Assert.assertEquals(actualResponse.getReminderList().get(1).getLinks().get(0).getRel(), RelValue.SELF.toString());
        Assert.assertEquals(actualResponse.getReminderList().get(1).getLinks().get(1).getHref(), CONTEXT_URI + RESOURCE_URI + "/2");
        Assert.assertEquals(actualResponse.getReminderList().get(1).getLinks().get(1).getMethod(), HttpMethod.PUT.toString());
    }

    @Test
    public void testGetAllRemindersByDueDate() {
        //mock values
        String dueDate = "123456789";
        String status = "";
        when(reminderDAO.selectByDueDateOrStatus(dueDate, status)).thenReturn(TestUtil.getReminderListByDueDate(dueDate));
        //make call
        Reminders actualResponse = reminderService.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getReminderList().size(), 1);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getStatus(), Status.DONE);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().size(), 2);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(0).getRel(), RelValue.SELF.toString());
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(1).getHref(), CONTEXT_URI + RESOURCE_URI + "/1");
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(1).getMethod(), HttpMethod.PUT.toString());
    }

    @Test
    public void testGetAllRemindersByStatus() {
        //mock values
        String dueDate = null;
        String status = "NOT_DONE";
        when(reminderDAO.selectByDueDateOrStatus(dueDate, status)).thenReturn(TestUtil.getReminderListByStatus(status));
        //make call
        Reminders actualResponse = reminderService.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getReminderList().size(), 1);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getStatus(), Status.NOT_DONE);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().size(), 2);
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(0).getRel(), RelValue.SELF.toString());
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(1).getHref(), CONTEXT_URI + RESOURCE_URI + "/2");
        Assert.assertEquals(actualResponse.getReminderList().get(0).getLinks().get(1).getMethod(), HttpMethod.PUT.toString());
    }

    @Test
    public void testGetAllRemindersByDueDateAndStatus() {
        //mock values
        String dueDate = "987654321";
        String status = "NOT_DONE";
        when(reminderDAO.selectByDueDateAndStatus(dueDate, status)).thenReturn(TestUtil.getReminderListByDueDateAndStatus(dueDate, status));
        //make call
        Reminders actualResponse = reminderService.getReminders(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.getReminderList().size(), 1);
    }

    @Test
    public void testGetAReminder() {
        //mock values
        String id = "1";
        when(reminderDAO.selectById(id)).thenReturn(TestUtil.getReminderById(id).get(0));
        //make call
        Reminder actualResponse = reminderService.getReminder(id);
        //assert response
        Assert.assertEquals(actualResponse.getStatus(), Status.DONE);
        Assert.assertEquals(actualResponse.getName(), "Travel");
    }

    @Test
    public void testUpdateReminder() {
        //mock values
        String id = "1";
        when(inputReminder.getName()).thenReturn("Meeting");
        List<ResourceLink> listResourceLink = HateoasLinkUtil.getHateoasLinkForUpdate(RESOURCE_URI, "/1");
        when(outputReminder.getLinks()).thenReturn(listResourceLink);
        when(outputReminder.getName()).thenReturn("Meeting");
        when(reminderDAO.selectById(id)).thenReturn(outputReminder);
        //make call
        Reminder actualResponse = reminderService.updateReminder(inputReminder, id);
        //assert response
        Assert.assertEquals(actualResponse.getName(), "Meeting");
        Assert.assertEquals(actualResponse.getLinks().size(), 2);
        Assert.assertEquals(actualResponse.getLinks().get(0).getRel(), RelValue.SELF.toString());
        Assert.assertEquals(actualResponse.getLinks().get(1).getHref(), CONTEXT_URI + RESOURCE_URI + "/1");
        Assert.assertEquals(actualResponse.getLinks().get(1).getMethod(), HttpMethod.GET.toString());
    }
}
