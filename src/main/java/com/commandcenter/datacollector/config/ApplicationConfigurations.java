package com.commandcenter.datacollector.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationConfigurations {
    @Value("${email}")
    public String email;
    @Value("${password}")
    public String emailPassword;
    @Value("recipients")
    public String recipients;
    @Value("folderName")
    public String folderName;
    @Value("interval")
    public int interval;
    @Value("smtpHost")
    public String smtpHost;
}
