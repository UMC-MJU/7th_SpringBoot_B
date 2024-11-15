package com.example.study.repository.member;

import com.example.study.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends MemberRepositoryCustom, JpaRepository<Member, Long> {


}
