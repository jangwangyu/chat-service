package org.example.chatservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.example.chatservice.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByChatroomId(Long chatroomId);

  Boolean existsByChatroomIdAndCreatedAtAfter(Long chatroomId, LocalDateTime createdAt);
}
