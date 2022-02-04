package com.commandcenter.datacollector.shutdown;

import com.commandcenter.datacollector.logger.Logger;
import com.commandcenter.datacollector.logger.email.EmailNotification;

public class ShutdownThread extends Thread {
    public void run() {
        Logger.LogInfo(String.valueOf(new Object() {
        }.getClass().getEnclosingClass().getSimpleName()), "Service Shutdown Initiated.");
//        Send email to inform that shutdown initiated.
        EmailNotification emailNotification = new EmailNotification("Auto Call Notification service has been stopped or crashed. Please notify Rana Waqas.");
        emailNotification.sendEmail();
    }
}
