package com.reminder.service.dao;

import com.reminder.service.mapper.ReminderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

/**
 * This class is used to return the SqlConnectionFactory
 */

public class MyBatisConnectionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "config/mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
               sqlSessionFactory.getConfiguration().addMapper(ReminderMapper.class);
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }


    /**
     * Returns the created sqlSessionFactory
     * @return sqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
