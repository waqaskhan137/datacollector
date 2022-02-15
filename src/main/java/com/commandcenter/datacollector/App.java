package com.commandcenter.datacollector;

import com.commandcenter.datacollector.plugins.inputs.email.MSExchange;
import com.commandcenter.datacollector.plugins.inputs.email.message.MessageList;
import com.commandcenter.datacollector.plugins.outputs.postgres.Postgres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class App {

    static Logger log = LogManager.getLogger(App.class.getName());

    @Autowired
    MSExchange msExchange;
    @Autowired
    Postgres postgres;

    public Postgres getPostgres() {
        return postgres;
    }

    @Autowired
    public void setPostgres(Postgres postgres) {
        this.postgres = postgres;
    }

    public MSExchange getMsExchange() {
        return msExchange;
    }

    @Autowired
    public void setMsExchange(MSExchange msExchange) {
        this.msExchange = msExchange;
    }

    public static void main(String[] args) {

        App app = new App();


        while (true) {

            app.getMsExchange().start();
            MessageList messageList = app.getMsExchange().fetch();
            //send data to postgres service for insertion.
            app.getPostgres().connect();
            for (int i = 0; i < messageList.getMessages().size(); i++) {
                app.getPostgres().insert(messageList.getMessages().get(i));
            }
            app.getPostgres().disconnect();
            app.getMsExchange().stop();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
