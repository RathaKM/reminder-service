## Reminder Service

This repo contains working code for a __Reminder service (REST)__ and a simple UI for consuming the resources. 

## Project Features

- CRUD operations
- Layered approach
- Dependency Injection using Spring
- Input data and Service validation
- Usage of Persistence framework
- Testing
  - Unit Testing
  - REST Client
  - Postman

## Technology Stack

| Description               | Tool/Framework    |
| --------------------------|-------------------|
| Java version              | JDK 1.8           |
| JAX-RS Implementation     | Jersey 2.23.2     |
| ORM                       | MyBatis 3.2.3     |
| Database                  | HSQLDB 2.4.0      |
| App Server                | Tomcat 7.0.47     |
| IDE                       | IntelliJ IDEA 14  |
| Build tool                | Maven 3.0.5       |
| Platform                  | iOS               |

## Application Design

![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/design.png)

## Reminder Service Resources

| Resource Type                         | Resource URI                                          |    HTTP Method |
| --------------------------------------|------------------------------------------------------ |----------------|
| Create Reminder                       | /reminder-service/v1/reminders                        | POST           |
| Update Reminder                       | /reminder-service/v1/reminders/id                     | PUT            |
| Get A Reminder                        | /reminder-service/v1/reminders/id                     | GET            |
| Get Reminders By DueDate and/or Status| /reminder-service/v1/reminders?dueDate=""&status=""   | GET            |
| Delete A Reminder                     | /reminder-service/v1/reminders/id                     | DELETE         |   

## Project Setup

### Service Setup

- Clone/fork this Repo and open/import this Project into IntelliJ or Eclipse
- Build/Package the project using _mvn install_
- Start Tomcat and Publish _reminder-service_
- Test the server by http://localhost:8080. This will show the Reminder Service UI page for consuming these resources. You may get error, if you try any of the resource, as the database setup is not done
- For a quick deployment you can use the shared [reminder-service-1.0-SNAPSHOT.war](../develop/reminder-service-1.0-SNAPSHOT.war) file, in case you didn't have time or ran into any issues. 

### Database Setup

- Install hsqldb 2.4.0
- Goto the 'hsqldb 2.4.0/hsqldb' folder and run the below command. This will start the server by creating a DB, _'reminderservdb'_
  - _java -cp ./lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:reminderservdb --dbname.0 reminderservdb_
  
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/hsqldb_server_start.png)

- Now, create the table, 'Reminder' by running the TestNG Test file, [_CreateTableTest.java_](../develop/src/test/java/com/reminder/service/integration/CreateTableTest.java)

## How to Consume Resources

There are 3 ways you can consume these resources. They are using simple jquery based UI, using Postman, and using REST client API. 

### Using UI

There is a simple jquery based UI implementation to consume these resources. 
- Load the page using http://localhost:8080
- Use the buttons by passing required inputs. Start with creating a Reminder as the DB is empty in the beginning. 

#### UI With Response 

![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/reminder_service_ui.png)

#### UI with Validation

![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/ui_with_validation.png)

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

#### Delete A Reminder
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/delete_reminder.png)

### Validation

#### Create Reminder - Missing Input Field - 'name'
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/missing_name.png)

#### Create Reminder - Invalid Input Field - 'name'
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/invalid_name.png)

#### Create Reminder - Invalid Input Fields - 'dueDate' and 'status'
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/invalid_duedate_status.png)

#### Get Reminder - Unavailable Data for the identifier
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/unavailable_item.png)

#### Get Reminder - Invalid Identifier
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/invalid_identifier.png)

#### Filter Reminder - Unavailable Data for the filters
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/data_unavailable_filter.png)

#### Update Reminder - Invalid Input Fields - 'dueDate' and 'status'
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/update_invalid_input.png)

#### Update Reminder - Invalid Input Fields and Invalid Identifier - 'dueDate' & 'status' and 'id'
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/update_invalid_input_id.png)

#### Delete Reminder - Unavailable Data for the identifier
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/delete_data_not_available.png)

#### Delete Reminder - Invalid Identifier
![alt txt](https://github.com/RathaKM/reminder-service/blob/develop/src/main/resources/images/delete_invalid_id.png)


### Using Rest Client
We can test the resources using Rest client API. These are available in the test folder [_RestClientTest.java_](../develop/src/test/java/com/reminder/service/integration/RestClientTest.java).

Please make sure that the Application and DB servers are running before running this client.

## How to Run Tests

There are some unit tests added into this repo

### Unit Tests

The unit tests are added mainly for Resource, Service and DAO layers. These tests will be run as part of the _mvn build/install_ command and can also be run by _mvn test_ command
 
