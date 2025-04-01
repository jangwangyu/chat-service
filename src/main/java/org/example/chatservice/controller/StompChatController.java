package org.example.chatservice.controller;

import java.security.Principal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class StompChatController {

  @MessageMapping("/chats/{chatroomId}") // /pub/chats
  @SendTo("/sub/chats/{chatroomId}")
  public ChatMessage handleMessage(@AuthenticationPrincipal Principal principal,@Payload Map<String, String> payload, @DestinationVariable Long chatroomId) {
    log.info("{} send {} in {}", principal.getName(), payload, chatroomId);
    
    return new ChatMessage(principal.getName(), payload.get("message"));
  }

}
