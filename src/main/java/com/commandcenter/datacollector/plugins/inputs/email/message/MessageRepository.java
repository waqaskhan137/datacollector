package com.commandcenter.datacollector.plugins.inputs.email.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySubject(String lastName);

    Message findById(long id);
}
