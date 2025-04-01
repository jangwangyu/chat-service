package org.example.chatservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dto.ChatroomDto;
import org.example.chatservice.entities.Chatroom;
import org.example.chatservice.services.ChatService;
import org.example.chatservice.vo.CustomOAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chats")
@RestController
public class ChatController {

  private final ChatService chatService;

  @PostMapping
  public ChatroomDto createChatroom(@AuthenticationPrincipal CustomOAuth2User user, @RequestParam String title) {
    Chatroom chatroom = chatService.createChatroom(user.getMember(), title);
    return ChatroomDto.from(chatroom);
  }

  @PostMapping("/{chatroomId}")
  public Boolean joinChatroom(@AuthenticationPrincipal CustomOAuth2User user, @PathVariable Long chatroomId) {
    return chatService.joinChatroom(user.getMember(), chatroomId);
  }

  @DeleteMapping("/{chatroomId}")
  public Boolean removeChatroom(@AuthenticationPrincipal CustomOAuth2User user, @PathVariable Long chatroomId) {
    return chatService.leaveChatroom(user.getMember(), chatroomId);
  }

  @GetMapping
  public List<ChatroomDto> getChatrooms(@AuthenticationPrincipal CustomOAuth2User user) {
    List<Chatroom> chatroomList = chatService.getChatroomList(user.getMember());
    return chatroomList.stream().map(ChatroomDto::from).toList();
  }
}
