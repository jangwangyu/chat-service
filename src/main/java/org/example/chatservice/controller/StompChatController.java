package org.example.chatservice.controller;

import java.security.Principal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class StompChatController {

  @MessageMapping("/chats") // /pub/chats
  @SendTo("/sub/chats")
  public ChatMessage handleMessage(@AuthenticationPrincipal Principal principal,@Payload Map<String, String> payload) {
    log.info("{} snt {}", principal.getName(), payload);
    
    return new ChatMessage(principal.getName(), payload.get("message"));
  }

}
