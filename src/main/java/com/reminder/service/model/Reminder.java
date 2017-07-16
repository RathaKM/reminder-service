package com.reminder.service.model;

import com.reminder.service.type.HttpMethod;
import com.reminder.service.type.RelValue;
import com.reminder.service.type.ReminderStatus;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
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
    @Size(min = 2, max = 25, message = "{reminder.name.length.invalid}")
    @NotNull (message = "{reminder.name.invalid}")
    private String name;
    // the short description of the Reminder
    @Size(min = 2, max = 200, message = "{reminder.description.invalid}")
    private String description;
    //dateTime in unix time
    @Min(value=Short.MAX_VALUE, message = "{reminder.dueDate.invalid}")
    @Max(value=Long.MAX_VALUE, message = "{reminder.dueDate.invalid}")
    private long dueDate;
    // the Status of the Reminder
    @NotNull (message = "{reminder.status.invalid}")
    private ReminderStatus status;

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

    public ReminderStatus getStatus() {
        return status;
    }

    public void setStatus(ReminderStatus status) {
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
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int)(dueDate ^ (dueDate >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        Reminder reminder = (Reminder) obj;
        if (this.id != reminder.id){
            return false;
        }
        if (this.name !=null && !this.name.equals(reminder.getName())){
            return false;
        }
        if (this.description !=null &&!this.description.equals(reminder.getDescription())){
            return false;
        }
        if (this.dueDate != reminder.getDueDate()){
            return false;
        }
        if (this.status !=null &&!this.status.equals(reminder.getStatus())){
            return false;
        }
        return true;
    }
}
