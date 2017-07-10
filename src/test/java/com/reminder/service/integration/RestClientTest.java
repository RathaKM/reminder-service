package com.reminder.service.integration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * This class is used to test quickly the POST method of a service
 */
public class RestClientTest {

    @Test
    public void testGetMethod() {

        Client client = Client.create();
        WebResource webResource = client
                .resource("http://localhost:8080/reminder-service/v1/reminders/1");
        String input = "{\"name\":\"Meeting\",\"description\":\"Meeting with Manager\",\"dueDate\":\"139964720\",\"status\":\"DONE\"}";

        /*String input = "<reminder>\n" +
                "\t<name>Meeting</name>\n" +
                "\t<description>Meeting with Manager</description>\n" +
                "\t<status>NOT_DONE</status>\n" +
                "</reminder>";
        ClientResponse response = webResource.type("application/xml")
                .post(ClientResponse.class, input); */

        ClientResponse response = webResource.type("application/json")
                .get(ClientResponse.class);
        Assert.assertEquals(response.getStatus(), 200);
        String output = response.getEntity(String.class);
        System.out.println("Response:" + output);
    }

    @Test
    public  void testPostMethod() {
        Client client = Client.create();
        WebResource webResource = client
                .resource("http://localhost:8080/reminder-service/v1/reminder/add");
        String input = "{\"name\":\"Meeting\",\"description\":\"Meeting with Manager\",\"dueDate\":\"139964720\",\"status\":\"DONE\"}";

        /*String input = "<reminder>\n" +
                "\t<name>Meeting</name>\n" +
                "\t<description>Meeting with Manager</description>\n" +
                "\t<status>NOT_DONE</status>\n" +
                "</reminder>";
        ClientResponse response = webResource.type("application/xml")
                .post(ClientResponse.class, input); */

        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);
        Assert.assertEquals(response.getStatus(), 201);
        String output = response.getEntity(String.class);
        System.out.println(output);
    }
}
