## Reminder Service

This repo contains working code for a __Reminder service (REST)__ and a simple UI for consuming the resources. 

## Technology Stack

| Description               | Tool/Framework    |
| --------------------------|-------------------|
| Java version              | JDK 1.8           |
| JAX-RS Implementation     | Jersey 2.23.2     |
| ORM                       | MyBatis 3.2.3     |
| Database                  | HSQLDB 2.4.0      |
| App Server                | Tomcat 7.0.47     |
| IDE                       | IntelliJ IDEA 14  |

## Reminder Service Resources

| Resource Type                         | Resource URI                                          |    HTTP Method |
| --------------------------------------|------------------------------------------------------ |----------------|
| Create Reminder                       | /reminder-service/v1/reminders                        | POST           |
| Update Reminder                       | /reminder-service/v1/reminders/id                     | PUT            |
| Get A Reminder                        | /reminder-service/v1/reminders/id                     | GET            |
| Get Reminders By DueDate and/or Status| /reminder-service/v1/reminders?dueDate=""&status=""   | GET            |

## Project Setup

### Service Setup

- Clone/fork this Repo and open/import this Project into IntelliJ or Eclipse
- Build/Package the project using _mvn install_
- Start Tomcat and Publish _reminder-service_
- Test the server by http://localhost:8080. This will show the Reminder Service UI page for consuming these resources. You may get error, if you try any of the resource, as the database setup is not done

### Database Setup

- Install hsqldb 2.4.0
- Goto the 'hsqldb 2.4.0/hsqldb' folder and run the below command. This will start the server by creating a DB, _'reminderservdb'_
  - _java -cp ./lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:reminderservdb --dbname.0 reminderservdb_
  
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/hsqldb_server_start.png)

- Now, create the table, 'Reminder' by running the TestNG Test file, [_CreateTableTest.java_](../develop/src/test/java/com/reminder/service/integration/CreateTableTest.java)

## How to Consume Resources

There are 2 ways you can consume these resources. 

### Using UI

There is a simple jquery based UI implementation to consume these resources. 
- Load the page using http://localhost:8080
- Use the buttons by passing required inputs. Start with creating a Reminder as the DB is empty in the beginning. Please note that there are no validations in place as of now and hence make sure that you pass the right inputs

![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/reminder_service_ui.png)

### Using Postman

The resources can be consumed by using below Postman collection
- https://www.getpostman.com/collections/ea448bb9db05b53e86c2 

#### Create A Reminder
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/create_reminder.png)

#### Update A Reminder
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/update_a_reminder.png)

#### Get A Reminder
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/get_a_reminder.png)

#### Get All Reminder
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/get_all_reminders.png)

#### Filter Reminders By DueDate
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/filter_reminder_by_duedate.png)

#### Filter Reminders By Status
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/filter_reminder_by_status.png)

#### Filter Reminders By DueDate And Status
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/filter_reminder_by_duedate_and_status.png)
