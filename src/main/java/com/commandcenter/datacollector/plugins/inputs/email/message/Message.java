package com.commandcenter.datacollector.plugins.inputs.email.message;

import lombok.Getter;
import lombok.Setter;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;

public class Message {
    @Getter
    @Setter
    String body;
    @Getter
    @Setter
    String subject;
    @Getter
    @Setter
    AttachmentCollection attachments;
    @Getter
    @Setter
    String from;
    @Getter
    @Setter
    String to;
    @Getter
    @Setter
    String messageDate;
}
