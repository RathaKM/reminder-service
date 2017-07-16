package com.reminder.service.dao;

import com.reminder.service.mapper.ReminderMapper;
import com.reminder.service.model.Reminder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * This class is used to manage the data with the data source, HSQLDB using myBatis
 */


public class ReminderDAO {

    private static SqlSessionFactory sqlSessionFactory;

    public ReminderDAO(){
        sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    /**
     * Returns the list of all Reminder instances from the database.
     * @return a List of Reminder instances
     */
    public List<Reminder> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            List<Reminder> list = mapper.selectAll();
            return list;
        } finally {
            session.close();
        }
    }

    /**
     * Returns a Reminder instance from the database based on the filter field, dueDate or status
     * @param dueDate the dueDate field used for lookup
     * @param status the status field used for lookup
     * @return a List of Reminder instances matching the filtering criteria
     */
    public List<Reminder> selectByDueDateOrStatus(String dueDate, String status){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            List<Reminder> list = mapper.selectByDueDateOrStatus(dueDate, status);
            return list;
        } finally {
            session.close();
        }
    }

    /**
     * Returns a Reminder instance from the database based on the filter fields , dueDate and status
     * @param dueDate the dueDate field used for lookup
     * @param status the status field used for lookup
     * @return a List of Reminder instances matching the filtering criteria
     */
    public List<Reminder> selectByDueDateAndStatus(String dueDate, String status){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            List<Reminder> list = mapper.selectByDueDateAndStatus(dueDate, status);
            return list;
        } finally {
            session.close();
        }
    }

    /**
     * Returns a Reminder instance from the database based on the primary key, id
     * @param id the primary key value used for lookup
     * @return A Reminder instance matching with the primary key value
     */
    public Reminder selectById(String id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            Reminder reminder = mapper.selectById(id);
            return reminder;
        } finally {
            session.close();
        }
    }

    /**
     * Updates an instance of Reminder in the database.
     * @param reminder the Reminder instance to be updated
     */
    public void update(Reminder reminder){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            mapper.update(reminder);
            session.commit();
        } finally {
            session.close();
        }
    }

    /**
     * Insert an instance of Reminder into the database.
     * @param reminder the instance of Reminder to be persisted
     */
    public void insert(Reminder reminder){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            mapper.insert(reminder);
            session.commit();
            System.out.println("Reminder Inserted into DB!");
        } finally {
            session.close();
        }
    }

    /**
     * Deletes an instance of Reminder in the database.
     * @param id the id of the Reminder instance to be deleted
     */
    public void delete(String id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ReminderMapper mapper = session.getMapper(ReminderMapper.class);
            mapper.delete(id);
            session.commit();
        } finally {
            session.close();
        }
    }
}
