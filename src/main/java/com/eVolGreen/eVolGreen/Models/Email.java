package com.eVolGreen.eVolGreen.Models;

import jakarta.mail.Address;

public class Email {
    private String destination;
    private String subject;
    private String body;

    public Email() { }

    public Email(String destination, String subject, String body) {
        this.destination = destination;
        this.subject = subject;
        this.body = body;

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
