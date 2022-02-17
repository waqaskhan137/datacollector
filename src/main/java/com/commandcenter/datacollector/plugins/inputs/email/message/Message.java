package com.commandcenter.datacollector.plugins.inputs.email.message;

import org.hibernate.annotations.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue()
    private long id;

    String body;
    String subject;
    Date date;

    public Message(String body, String subject, Date date) {
        this.body = body;
        this.subject = subject;
        this.date = date;
    }

    public Message() {

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

