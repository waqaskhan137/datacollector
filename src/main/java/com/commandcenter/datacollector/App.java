package com.commandcenter.datacollector;

import com.commandcenter.datacollector.plugins.inputs.email.MSExchange;
import com.commandcenter.datacollector.plugins.outputs.postgres.Postgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

    //    static Logger log = LogManager.getLogger(class.getName());
    private AnnotationConfigApplicationContext context;

    @Autowired
    static Postgres postgres;
    @Autowired
    static MSExchange msExchange;

    public static void main(String[] args) {
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.start();
    }
}
