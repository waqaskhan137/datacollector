package com.commandcenter.datacollector.plugins.inputs;

import com.commandcenter.datacollector.plugins.inputs.email.message.MessageList;
import org.springframework.stereotype.Component;

@Component
public interface Input {
    /**
     * Start the input.
     */
    public void start();

    /**
     * Get the messages from the input.
     *
     * @return
     */
    public MessageList fetch();

    /**
     * Stop the input.
     */
    public void stop();
}
