package org.example.chatservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dto.ChatroomDto;
import org.example.chatservice.dto.MemberDto;
import org.example.chatservice.services.ConsultantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/consultants")
@Controller
public class ConsultantController {

  private final ConsultantService customUserDetailsService;

  @ResponseBody
  @PostMapping
  public MemberDto saveMember(@RequestBody MemberDto memberDto) {
    return customUserDetailsService.saveMember(memberDto);
  }

  @GetMapping
  public String index() {
    return "/consultant/index.html";
  }

  @ResponseBody
  @GetMapping("/chats")
  public Page<ChatroomDto> getChatroomPage(Pageable pageable) {
    return customUserDetailsService.getChatroomPage(pageable);
  }

}
