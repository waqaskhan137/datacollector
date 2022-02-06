package com.commandcenter.datacollector.plugins.inputs;

public interface Input {
    public void start();
    public void fetch();
    public void stop();
}
