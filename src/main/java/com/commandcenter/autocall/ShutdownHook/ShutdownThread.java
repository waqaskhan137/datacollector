package com.commandcenter.autocall.ShutdownHook;

import com.commandcenter.autocall.cclogger.CCLogger;
import com.commandcenter.autocall.cclogger.email.EmailNotification;

public class ShutdownThread extends Thread {
    public void run() {
        CCLogger.LogInfo(String.valueOf(new Object() {
        }.getClass().getEnclosingClass().getSimpleName()), "Service Shutdown Initiated.");
//        Send email to inform that shutdown initiated.
        EmailNotification emailNotification = new EmailNotification("Auto Call Notification service has been stopped or crashed. Please notify Rana Waqas.");
        emailNotification.sendEmail();
    }
}
