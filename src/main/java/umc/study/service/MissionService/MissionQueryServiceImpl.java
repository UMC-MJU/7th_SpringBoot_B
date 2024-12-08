package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository.MemberRepository; // Member를 조회하기 위해 추가
import umc.study.domain.enums.MissionStatus;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository; // MemberRepository 추가

    @Override
    public Page<MemberMission> getOngoingMissions(Long memberId, Integer page, int pageSize) {
        // memberId를 이용해 Member 객체를 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        // findAllByMemberAndStatus 메서드 사용
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, pageRequest);
    }
}
