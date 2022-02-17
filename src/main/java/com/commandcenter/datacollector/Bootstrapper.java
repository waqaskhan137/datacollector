package com.commandcenter.datacollector;

import com.commandcenter.datacollector.plugins.inputs.email.MSExchange;
import com.commandcenter.datacollector.plugins.outputs.postgres.Postgres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Bootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrapper.class);
    private AnnotationConfigApplicationContext context;

    public void start() {
        LOGGER.info("Starting the application");
        context = new AnnotationConfigApplicationContext();
        context.scan("com.commandcenter.datacollector");
        context.refresh();

        var msExchange = context.getBean(MSExchange.class);
        var postgres = context.getBean(Postgres.class);

        while (true) {
            LOGGER.info("Started the MS Exchange plugin");
            msExchange.start();
            msExchange.fetch();
            msExchange.stop();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                LOGGER.info("InterruptedException: {}", e.getMessage());
            }
        }
    }
}
