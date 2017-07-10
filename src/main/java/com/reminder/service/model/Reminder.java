package com.reminder.service.model;

import com.reminder.service.type.HttpMethod;
import com.reminder.service.type.RelValue;
import com.reminder.service.type.Status;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is a Reminder model
 */

@XmlRootElement
@XmlType(propOrder={"id", "name", "description", "dueDate", "status", "links"})
public class Reminder {
    // the auto generated primary key field
    private int id;
    // the name of the Reminder
    private String name;
    // the short description of the Reminder
    private String description;
    //dateTime in unix time
    private long dueDate;
    // the Status of the Reminder
    private Status status;

    private List<ResourceLink> links = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setLinks(List<ResourceLink> links) {
        this.links = links;
    }

    public List<ResourceLink> getLinks(){
        return this.links;
    }

    public void addResourceLink (final String href, final HttpMethod method, final RelValue rel) {
        this.links.add(new ResourceLink(href, method, rel));
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Reminder reminder = (Reminder) obj;
        if (this.id != reminder.id){
            return false;
        }
        if (!this.name.equals(reminder.getName())){
            return false;
        }
        if (!this.description.equals(reminder.getDescription())){
            return false;
        }
        if (this.dueDate != reminder.getDueDate()){
            return false;
        }
        if (!this.status.equals(reminder.getStatus())){
            return false;
        }
        return true;
    }
}
