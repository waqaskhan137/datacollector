package com.commandcenter.datacollector.plugins.inputs.email.message;

import java.util.Date;

public record Message(String body, String subject, Date date) {
}

