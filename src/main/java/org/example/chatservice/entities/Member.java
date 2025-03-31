package org.example.chatservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.chatservice.enums.Gender;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  @Id
  Long id;

  String email;
  String nickName;
  String name;
  @Enumerated(EnumType.STRING)
  Gender gender;
  String phoneNumber;
  LocalDate birthDay;
  String role;

}
