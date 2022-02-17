package com.commandcenter.datacollector.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Configurations {
    @Value("${email}")
    public String email;
    @Value("${password}")
    public String emailPassword;
    @Value("${recipients}")
    public String recipients;
    @Value("${folderName}")
    public String folderName;
    @Value("${interval}")
    public String interval;
    @Value("${smtpHost}")
    public String smtpHost;
    @Value("${postgresHost}")
    public String postgresHost;
    @Value("${postgresPort}")
    public String postgresPort;
    @Value("${postgresUser}")
    public String postgresUser;
    @Value("${postgresPassword}")
    public String postgresPassword;
}
