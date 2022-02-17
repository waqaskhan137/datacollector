package com.commandcenter.datacollector.plugins.inputs.email.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findBySubject(String lastName);

    Message findById(long id);
}
