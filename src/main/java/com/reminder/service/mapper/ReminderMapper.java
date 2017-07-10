package com.reminder.service.mapper;

import com.reminder.service.model.Reminder;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * This class is used to map the methods with Sql queries
 */
public interface ReminderMapper {

    final String SELECT_ALL = "SELECT * FROM REMINDER";
    final String SELECT_BY_ID = "SELECT * FROM REMINDER WHERE ID = #{id}";
    final String SELECT_BY_DUE_DATE_OR_STATUS = "SELECT * FROM REMINDER WHERE cast(DUE_DATE as varchar(20)) = #{dueDate} OR STATUS = #{status}";
    final String SELECT_BY_DUE_DATE_AND_STATUS = "SELECT * FROM REMINDER WHERE DUE_DATE = #{dueDate} AND STATUS = #{status}";
    final String UPDATE = "UPDATE REMINDER SET NAME = #{name}, DESCRIPTION = #{description}, DUE_DATE = #{dueDate}, STATUS = #{status} WHERE ID = #{id}";
    final String INSERT = "INSERT INTO REMINDER (NAME, DESCRIPTION, DUE_DATE, STATUS) VALUES (#{name}, #{description}, #{dueDate}, #{status})";

    /**
     * Returns a Reminder instance from the database based on the filter fields.
     * @param dueDate the dueDate field used for lookup
     * @param status the status field used for lookup
     * @return a List of Reminder instances matching the filtering criteria
     */
    @Select(SELECT_BY_DUE_DATE_OR_STATUS)
    @Results(value = {
            @Result(property="id", column="ID"),
            @Result(property="name", column="NAME"),
            @Result(property="description", column="DESCRIPTION"),
            @Result(property="dueDate", column="DUE_DATE"),
            @Result(property="status", column="STATUS")
    })
    List<Reminder> selectByDueDateOrStatus(@Param("dueDate")String dueDate, @Param("status")String status);

    /**
     * Returns a Reminder instance from the database based on the filter fields.
     * @param dueDate the dueDate field used for lookup
     * @param status the status field used for lookup
     * @return a List of Reminder instances matching the filtering criteria
     */
    @Select(SELECT_BY_DUE_DATE_AND_STATUS)
    @Results(value = {
            @Result(property="id", column="ID"),
            @Result(property="name", column="NAME"),
            @Result(property="description", column="DESCRIPTION"),
            @Result(property="dueDate", column="DUE_DATE"),
            @Result(property="status", column="STATUS")
    })
    List<Reminder> selectByDueDateAndStatus(@Param("dueDate")String dueDate, @Param("status")String status);

    /**
     * Returns a Reminder instance from the database based on the primary key, id
     * @param id the primary key value used for lookup
     * @return A Reminder instance matching with the primary key value
     */
    @Select(SELECT_BY_ID)
    @Results(value = {
            @Result(property="id", column="ID"),
            @Result(property="name", column="NAME"),
            @Result(property="description", column="DESCRIPTION"),
            @Result(property="dueDate", column="DUE_DATE"),
            @Result(property="status", column="STATUS")
    })
    Reminder selectById(@Param("id")String id);

    /**
     * Returns the list of all Reminder instances from the database.
     * @return a List of Reminder instances
     */
    @Select(SELECT_ALL)
    @Results(value = {
            @Result(property="id", column="ID"),
            @Result(property="name", column="NAME"),
            @Result(property="description", column="DESCRIPTION"),
            @Result(property="dueDate", column="DUE_DATE"),
            @Result(property="status", column="STATUS")
    })
    List<Reminder> selectAll();

    /**
     * Updates an instance of Reminder in the database.
     * @param reminder the Reminder instance to be updated
     */
    @Update(UPDATE)
    void update(Reminder reminder);

    /**
     * Insert an instance of Reminder into the database.
     * @param reminder the instance of Reminder to be persisted
     */
    @Insert(INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    void insert(Reminder reminder);
}