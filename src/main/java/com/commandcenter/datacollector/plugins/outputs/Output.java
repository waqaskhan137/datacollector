package com.commandcenter.datacollector.plugins.outputs;

import com.commandcenter.datacollector.plugins.inputs.email.message.Message;

public interface Output {
    public void connect();

    public void disconnect();

    public void insert(Message data);

}
