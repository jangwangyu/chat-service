package org.example.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chatroom {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chatroom_id")
  @Id
  Long id;

  String title;

  @OneToMany(mappedBy = "chatroom")
  Set<MemberChatroomMapping> memberChatroomMappingSet;

  @Transient
  Boolean hasNewMessage;

  LocalDateTime createdAt;

  public MemberChatroomMapping addMember(Member member) {
    if (this.getMemberChatroomMappingSet() == null) {
      this.memberChatroomMappingSet = new HashSet<>();
    }

    MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
        .member(member)
        .chatroom(this)
        .build();

    this.memberChatroomMappingSet.add(memberChatroomMapping);

    return memberChatroomMapping;
  }

}
