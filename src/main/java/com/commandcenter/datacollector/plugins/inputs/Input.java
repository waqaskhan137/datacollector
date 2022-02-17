package com.commandcenter.datacollector.plugins.inputs;

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
    public void fetch();

    /**
     * Stop the input.
     */
    public void stop();
}
