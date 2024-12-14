package com.example.study.repository;

import com.example.study.domain.Member;
import com.example.study.domain.MemberMission;
import com.example.study.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Boolean existsByMemberAndMission(Member member, Mission mission);
}
