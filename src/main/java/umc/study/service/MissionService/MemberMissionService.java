package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.MemberMissionRepository;
import umc.study.web.dto.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public void challengeMission(MemberMissionRequestDTO request) {
        // 회원 조회
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + request.getMemberId()));

        // 미션 조회
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("Mission not found with ID: " + request.getMissionId()));

        // 중복 도전 방지
        boolean exists = memberMissionRepository.existsByMemberAndMission(member, mission);
        if (exists) {
            throw new IllegalStateException("The member has already challenged this mission.");
        }

        // MemberMission 생성 및 저장
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING) // 기본 상태 설정
                .build();

        memberMissionRepository.save(memberMission);
    }
}
