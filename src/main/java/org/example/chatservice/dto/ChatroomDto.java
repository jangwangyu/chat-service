package org.example.chatservice.dto;

import java.time.LocalDateTime;
import org.example.chatservice.entities.Chatroom;

public record ChatroomDto(
    Long id,
    String title,
    Integer memberCount,
    LocalDateTime createdAt) {

  public static ChatroomDto from(Chatroom chatroom) {
    return new ChatroomDto(chatroom.getId(), chatroom.getTitle(),
        chatroom.getMemberChatroomMappingSet().size(),
        chatroom.getCreatedAt());
  }
}
