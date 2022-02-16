package com.commandcenter.datacollector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Data Collector");
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.start();
    }
}
