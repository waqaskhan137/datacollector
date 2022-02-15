package com.commandcenter.datacollector.plugins.inputs.email.message;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MessageList {
    
    private ArrayList<Message> messages;
    private static MessageList single_instance = null;

    public MessageList() {
        messages = new ArrayList<>();
    }

    public static MessageList getInstance() {

        if (single_instance == null) single_instance = new MessageList();

        return single_instance;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void add(Message message) {
        if (messages == null) messages = new ArrayList<>();
        getMessages().add(message);
    }

    public void remove(Message message) {
        getMessages().remove(message);
    }

    public void removeAll() {
        getInstance().getMessages().clear();
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
