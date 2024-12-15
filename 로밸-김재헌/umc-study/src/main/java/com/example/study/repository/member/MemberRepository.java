package com.example.study.repository.member;

import com.example.study.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends MemberRepositoryCustom, JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
