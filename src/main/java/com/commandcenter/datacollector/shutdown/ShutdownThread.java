package com.commandcenter.datacollector.shutdown;

import com.commandcenter.datacollector.notifier.email.EmailNotification;

public class ShutdownThread extends Thread {
    public void run() {
//        Send email to inform that shutdown initiated.
        EmailNotification emailNotification = new EmailNotification("Auto Call Notification service has been stopped or crashed. Please notify Rana Waqas.");
        emailNotification.sendEmail();
    }
}
