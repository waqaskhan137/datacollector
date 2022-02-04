package com.commandcenter.datacollector.config;

public class ApplicationConfigurations {
    static String email;
    static String password;
    static String recipients;
    static String folderName;
    static int interval;


    public static int getInterval() {
        return interval;
    }

    public static void setInterval(int interval) {
        ApplicationConfigurations.interval = interval;
    }


    public static String getFolderName() {
        return folderName;
    }

    public static void setFolderName(String folderName) {
        ApplicationConfigurations.folderName = folderName;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
}
