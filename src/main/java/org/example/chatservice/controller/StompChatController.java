package org.example.chatservice.controller;

import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dto.ChatMessage;
import org.example.chatservice.entities.Message;
import org.example.chatservice.services.ChatService;
import org.example.chatservice.vo.CustomOAuth2User;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StompChatController {

  private final ChatService chatService;
  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/chats/{chatroomId}") // /pub/chats
  @SendTo("/sub/chats/{chatroomId}")
  public ChatMessage handleMessage(@AuthenticationPrincipal Principal principal,@Payload Map<String, String> payload, @DestinationVariable Long chatroomId) {
    log.info("{} send {} in {}", principal.getName(), payload, chatroomId);
    CustomOAuth2User user = (CustomOAuth2User) ((AbstractAuthenticationToken)principal).getPrincipal();
    Message message = chatService.saveMessage(user.getMember(), payload.get("message"),chatroomId);
    messagingTemplate.convertAndSend("/sub/chats/updates", chatService.getChatroom(chatroomId));
    return new ChatMessage(principal.getName(), payload.get("message"));
  }

}
