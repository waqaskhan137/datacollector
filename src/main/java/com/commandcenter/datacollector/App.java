package com.commandcenter.datacollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    static Logger log = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
//        Logger.LogInfo(new Object() {
//        }.getClass().getEnclosingClass().getSimpleName(), "------- Starting the FTP Download Thread ------");
////        AutoCallHandler autoCallHandler = new AutoCallHandler();
//
////        Thread autoCallHandlerThread = new Thread(autoCallHandler);
//        Thread autoCallHandlerThread = new Thread();
//        autoCallHandlerThread.setName("Auto Call Handler Thread");
//        autoCallHandlerThread.start();import org.apache.log4j.Logger; org.apache.logging.log4j.Logger
//        Logger.LogInfo(new Object() {
//        }.getClass().getEnclosingClass().getSimpleName(), "---------Thread has been started ---------");
//
//        Logger.LogInfo(new Object() {
//        }.getClass().getEnclosingClass().getSimpleName(), "Adding shutdown hook for the auto call notification service.");
//        Runtime r = Runtime.getRuntime();
//        r.addShutdownHook(new ShutdownThread());
        System.out.println("Hello World");
        log.info("Starting the application");
    }
}
