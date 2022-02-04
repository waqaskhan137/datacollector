package com.commandcenter.datacollector.logger.email;

import com.commandcenter.datacollector.config.ApplicationConfigurations;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotification {
    static Logger log = LogManager.getLogger(EmailNotification.class.getName());

    String exception;
    private static final String recipients = ApplicationConfigurations.getRecipients();
    private static final String SMTP_SERVER = "afinitismtp.afiniti.com";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static final String EMAIL_FROM = "ps.autocallnotification@afiniti.com";

    private static final String EMAIL_SUBJECT = "Auto Call Notification V5 - Exception Alert.";

    public EmailNotification(String exception) {
        this.exception = exception;
    }

    public void sendEmail() {


        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            msg.setHeader("X-Priority", "1");
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients, false));

            msg.setSubject(EMAIL_SUBJECT);

            //TEXT email
            msg.setText(exception);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            log.error("Exception [ " + e.getCause() + " ]", e);
        }

    }
}
