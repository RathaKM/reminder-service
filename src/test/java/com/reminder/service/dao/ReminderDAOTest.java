package com.reminder.service.dao;

import com.reminder.service.mapper.ReminderMapper;
import com.reminder.service.model.Reminder;
import com.reminder.service.test.util.TestUtil;
import com.reminder.service.type.Status;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

/**
 * Test class for ReminderDAO
 */

@PrepareForTest({MyBatisConnectionFactory.class})
public class ReminderDAOTest extends PowerMockTestCase {

    private ReminderDAO reminderDAO;

    @Mock
    private SqlSessionFactory sqlSessionFactory;
    @Mock
    private MyBatisConnectionFactory myBatisConnectionFactory;
    @Mock
    private ReminderMapper reminderMapper;
    @Mock
    private SqlSession sqlSession;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(MyBatisConnectionFactory.class);
        PowerMockito.when(MyBatisConnectionFactory.getSqlSessionFactory()).thenReturn(sqlSessionFactory);
        reminderDAO = new ReminderDAO();
    }

    @Test
    public void testSelectAll() {
        //mock values
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.when(reminderMapper.selectAll()).thenReturn(TestUtil.getReminderList());
        //make call
        List<Reminder> actualResponse = reminderDAO.selectAll();
        //assert response
        Assert.assertEquals(actualResponse.size(), 2);
        Assert.assertEquals(actualResponse.get(0).getStatus(), Status.DONE);
        Assert.assertEquals(actualResponse.get(1).getStatus(), Status.NOT_DONE);
    }

    @Test
    public void testSelectByDueDate() {
        //mock values
        String dueDate = "123456789";
        String status = "";
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.when(reminderMapper.selectByDueDateOrStatus(dueDate, status)).thenReturn(TestUtil.getReminderListByDueDate(dueDate));
        //make call
        List<Reminder> actualResponse = reminderDAO.selectByDueDateOrStatus(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.size(), 1);
        Assert.assertEquals(actualResponse.get(0).getName(), "Travel");
        Assert.assertEquals(actualResponse.get(0).getStatus(), Status.DONE);
    }

    @Test
    public void testSelectByStatus() {
        //mock values
        String dueDate = null;
        String status = "DONE";
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.when(reminderMapper.selectByDueDateOrStatus(dueDate, status)).thenReturn(TestUtil.getReminderListByStatus(status));
        //make call
        List<Reminder> actualResponse = reminderDAO.selectByDueDateOrStatus(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.size(), 1);
        Assert.assertEquals(actualResponse.get(0).getName(), "Travel");
        Assert.assertEquals(actualResponse.get(0).getStatus(), Status.DONE);
    }

    @Test
    public void testSelectByDueDateAndStatus() {
        //mock values
        String dueDate = "987654321";
        String status = "NOT_DONE";
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.when(reminderMapper.selectByDueDateAndStatus(dueDate, status)).thenReturn(TestUtil.getReminderListByDueDateAndStatus(dueDate, status));
        //make call
        List<Reminder> actualResponse = reminderDAO.selectByDueDateAndStatus(dueDate, status);
        //assert response
        Assert.assertEquals(actualResponse.size(), 1);
        Assert.assertEquals(actualResponse.get(0).getName(), "Shopping");
        Assert.assertEquals(actualResponse.get(0).getStatus(), Status.NOT_DONE);
    }

    @Test
    public void testSelectById() {
        //mock values
        String id = "1";
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.when(reminderMapper.selectById(id)).thenReturn(TestUtil.getReminderById(id).get(0));
        //make call
        Reminder actualResponse = reminderDAO.selectById(id);
        //assert response
        Assert.assertEquals(actualResponse.getName(), "Travel");
        Assert.assertEquals(actualResponse.getStatus(), Status.DONE);
    }

    @Test
    public void testUpdate() {
        //mock values
        String id = "1";
        Reminder reminder = TestUtil.getReminderById(id).get(0);
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.doNothing().doThrow(new IllegalArgumentException()).when(reminderMapper).update(reminder);

        //make call
        reminderDAO.update(reminder);
        //assert response
        Mockito.verify(reminderMapper).update(reminder);
    }

    @Test
    public void testInsert() {
        //mock values
        String id = "1";
        Reminder reminder = TestUtil.getReminderById(id).get(0);
        PowerMockito.when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        PowerMockito.when(sqlSession.getMapper(ReminderMapper.class)).thenReturn(reminderMapper);
        PowerMockito.doNothing().doThrow(new IllegalArgumentException()).when(reminderMapper).insert(reminder);

        //make call
        reminderDAO.insert(reminder);
        //assert response
        Mockito.verify(reminderMapper).insert(reminder);
    }
}
