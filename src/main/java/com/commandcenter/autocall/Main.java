package com.commandcenter.autocall;

import com.commandcenter.autocall.Processing.AutoCallHandler;
import com.commandcenter.autocall.ShutdownHook.ShutdownThread;
import com.commandcenter.autocall.cclogger.CCLogger;

public class Main {

    public static void main(String[] args) {
        CCLogger.LogInfo(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "------- Starting the FTP Download Thread ------");
        AutoCallHandler autoCallHandler = new AutoCallHandler();

        Thread autoCallHandlerThread = new Thread(autoCallHandler);
        autoCallHandlerThread.setName("Auto Call Handler Thread");
        autoCallHandlerThread.start();
        CCLogger.LogInfo(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "---------Thread has been started ---------");

        CCLogger.LogInfo(new Object() {
        }.getClass().getEnclosingClass().getSimpleName(), "Adding shutdown hook for the auto call notification service.");
        Runtime r = Runtime.getRuntime();
        r.addShutdownHook(new ShutdownThread());

    }
}
