package com.ll.exam.spring_restapi.app.member.repository;

import com.ll.exam.spring_restapi.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
