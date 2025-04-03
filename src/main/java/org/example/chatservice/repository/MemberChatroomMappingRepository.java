package org.example.chatservice.repository;

import java.util.List;
import java.util.Optional;
import org.example.chatservice.entities.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberChatroomMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {

  boolean existsByMemberIdAndChatroomId(Long memberId, Long chatroomId);

  void deleteByMemberIdAndChatroomId(Long memberId, Long chatroomId);

  List<MemberChatroomMapping> findAllByMemberId(Long memberId);

  Optional<MemberChatroomMapping> findByMemberIdAndChatroomId(Long memberId, Long currentChatroomId);
}
