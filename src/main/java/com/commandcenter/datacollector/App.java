package com.commandcenter.datacollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    static Logger log = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {

        while (true) {
            // TODO: 2/6/2022 fixing the logs levels
            log.info("This from log4j info level");
            log.warn("This from log4j warn level");
            log.error("This from log4j error level");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
