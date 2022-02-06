package com.commandcenter.datacollector.plugins.inputs.email;

import com.commandcenter.datacollector.plugins.inputs.Input;
import com.commandcenter.datacollector.plugins.inputs.email.message.MessageList;

public class IMAP implements Input {
    @Override
    public void start() {

    }

    @Override
    public MessageList fetch() {
        return null;
    }

    @Override
    public void stop() {

    }
}
