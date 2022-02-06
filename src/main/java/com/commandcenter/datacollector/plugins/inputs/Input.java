package com.commandcenter.datacollector.plugins.inputs;

import com.commandcenter.datacollector.plugins.inputs.email.message.MessageList;

public interface Input {
    public void start();
    public MessageList fetch();
    public void stop();
}
