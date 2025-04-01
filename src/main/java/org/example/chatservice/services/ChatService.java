package org.example.chatservice.services;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.entities.Chatroom;
import org.example.chatservice.entities.Member;
import org.example.chatservice.entities.MemberChatroomMapping;
import org.example.chatservice.repository.ChatroomRepository;
import org.example.chatservice.repository.MemberChatroomMappingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

  private final ChatroomRepository chatroomRepository;
  private final MemberChatroomMappingRepository memberChatroomMappingRepository;

  public Chatroom createChatroom(Member member,String title) {
    Chatroom chatroom = Chatroom.builder()
        .title(title)
        .createdAt(LocalDateTime.now())
        .build();
    chatroom = chatroomRepository.save(chatroom); // 채팅방 생성

    MemberChatroomMapping memberChatroomMapping = chatroom.addMember(member);

    memberChatroomMapping = memberChatroomMappingRepository.save(memberChatroomMapping); // 채팅방을 생성한 유저 참여

    return chatroom;
  }

  public Boolean joinChatroom(Member member, Long chatroomId) {
    if(memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)){
      log.info("이미 참여한 채팅방입니다.");
      return false;
    } // 채팅방이 있을경우

    Chatroom chatroom = chatroomRepository.findById(chatroomId).get();

    MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
        .member(member)
        .chatroom(chatroom)
        .build();

    memberChatroomMapping = memberChatroomMappingRepository.save(memberChatroomMapping); // 채팅방이 없어서 참여가 가능한 경우
    return true;
  }

  @Transactional
  public Boolean leaveChatroom(Member member, Long chatroomId) {
    if(!memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)){
      log.info("참여하지 않은 방입니다.");
      return false;
    }

    memberChatroomMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);

    return true;
  }

  public List<Chatroom> getChatroomList(Member member) {
    List<MemberChatroomMapping> memberChatroomMappingList = memberChatroomMappingRepository.findAllByMemberId(member.getId());

    return memberChatroomMappingList.stream()
        .map(MemberChatroomMapping::getChatroom)
        .toList();
  }
}
