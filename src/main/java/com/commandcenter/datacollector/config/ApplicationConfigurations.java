package com.commandcenter.datacollector.config;

import lombok.Getter;
import lombok.Setter;

public class ApplicationConfigurations {
    @Getter @Setter
    static String email;
    @Getter @Setter
    static String password;
    @Getter @Setter
    static String recipients;
    @Getter @Setter
    static String folderName;
    @Getter @Setter
    static int interval;
    @Getter @Setter
    static String smtpHost;
    @Getter @Setter
    static String userName;
}
