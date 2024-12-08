package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.Mission;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 특정 회원의 특정 상태 미션 조회 메서드
    List<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status);

    // 특정 회원과 미션의 존재 여부 확인 메서드 추가
    boolean existsByMemberAndMission(Member member, Mission mission);

    // 특정 회원의 미션을 상태별로 페이지네이션하여 조회하는 메서드
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}

