package com.commandcenter.datacollector.notifier.email;

import com.commandcenter.datacollector.config.ApplicationConfigurations;
import com.commandcenter.datacollector.notifier.Notifier;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Notification implements Notifier {
    static Logger log = LogManager.getLogger(Notification.class.getName());

    @Autowired
    ApplicationConfigurations applicationConfigurations;

    private String message;
    private String subject;


    private static final String EMAIL_FROM = "datacollector@luminent.com";

    public Notification(String message) {
        this.message = message;
    }

    public void sendEmail() {


        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            msg.setHeader("X-Priority", "1");
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(applicationConfigurations.recipients, false));

            msg.setSubject(subject);

            //TEXT email
            msg.setText(message);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(applicationConfigurations.smtpHost, applicationConfigurations.email, applicationConfigurations.emailPassword);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            log.error("Exception [ " + e.getCause() + " ]", e);
        }

    }

    @Override
    public void notify(String message, String subject) {
        this.message = message;
        this.subject = subject;
        sendEmail();
    }
}
