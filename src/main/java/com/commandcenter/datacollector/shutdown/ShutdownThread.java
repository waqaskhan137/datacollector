package com.commandcenter.datacollector.shutdown;

import com.commandcenter.datacollector.notifier.email.Notification;

public class ShutdownThread extends Thread {
    public void run() {
//        Send email to inform that shutdown initiated.
        Notification notification = new Notification("Auto Call Notification service has been stopped or crashed. Please notify Rana Waqas.");
        notification.sendEmail();
    }
}
