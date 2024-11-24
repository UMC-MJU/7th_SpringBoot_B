package com.example.study.service.member;

import com.example.study.domain.Member;
import com.example.study.web.dto.MemberRequestDto;
import org.springframework.stereotype.Service;


public interface MemberCommandService {
    public Member joinMember(MemberRequestDto.JoinDto request);
    public Member find(Long id);
}
