package com.commandcenter.datacollector;

import com.commandcenter.datacollector.shutdown.ShutdownThread;
import com.commandcenter.datacollector.logger.Logger;

public class App {

    public static void main(String[] args) {
        Logger.LogInfo(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "------- Starting the FTP Download Thread ------");
//        AutoCallHandler autoCallHandler = new AutoCallHandler();

//        Thread autoCallHandlerThread = new Thread(autoCallHandler);
        Thread autoCallHandlerThread = new Thread();
        autoCallHandlerThread.setName("Auto Call Handler Thread");
        autoCallHandlerThread.start();
        Logger.LogInfo(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "---------Thread has been started ---------");

        Logger.LogInfo(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "Adding shutdown hook for the auto call notification service.");
        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new ShutdownThread());

    }
}
