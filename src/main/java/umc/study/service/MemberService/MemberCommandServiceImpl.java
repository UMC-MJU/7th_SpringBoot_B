package umc.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResponseDTO completeMission(Long memberId, Long missionId) {
        // MemberMission 조회
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 도전 정보가 없습니다."));

        // 상태 변경
        if (memberMission.getStatus() == MissionStatus.COMPLETE) {
            throw new IllegalStateException("이미 완료된 미션입니다.");
        }

        memberMission.setStatus(MissionStatus.COMPLETE);

        // 저장 후 응답 생성
        return MemberMissionResponseDTO.builder()
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .build();
    }

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberResponseDTO.MemberMissionPreViewDTO challengeMission(Long memberId, Long missionId) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("미션이 존재하지 않습니다."));

        // MemberMission 생성 및 추가
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        member.getMemberMissionList().add(memberMission);

        // 변경 감지에 의해 저장됨
        memberRepository.save(member);

        // 응답 생성
        MemberResponseDTO.MemberMissionDTO missionDTO = MemberResponseDTO.MemberMissionDTO.builder()
                .missionId(mission.getId())
                .deadline(mission.getDeadline())
                .reward(mission.getReward())
                .status(MissionStatus.CHALLENGING.name())
                .build();

        return MemberResponseDTO.MemberMissionPreViewDTO.builder()
                .mssionList(Collections.singletonList(missionDTO))
                .listSize(1)
                .totalPage(1)
                .totalElements(1L)
                .isFirst(true)
                .isLast(true)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponseDTO> getMyReviews(Long memberId) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // 연관된 리뷰 목록 조회
        return member.getReviewList().stream()
                .map(review -> ReviewResponseDTO.builder()
                        .reviewId(review.getId())
                        .title(review.getTitle())
                        .body(review.getBody())
                        .score(review.getScore())
                        .createdAt(review.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MemberResponseDTO.MemberMissionDTO> getMyChallengingMissions(Long memberId, int page, int size) {
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // 진행 중인 미션 필터링
        List<MemberResponseDTO.MemberMissionDTO> challengingMissions = member.getMemberMissionList().stream()
                .filter(memberMission -> memberMission.getStatus() == MissionStatus.CHALLENGING) // CHALLENGING 상태 필터링
                .map(memberMission -> MemberResponseDTO.MemberMissionDTO.builder()
                        .missionId(memberMission.getMission().getId())
//                        .missionName(memberMission.getMission().getName())
                        .deadline(memberMission.getMission().getDeadline())
                        .reward(memberMission.getMission().getReward())
                        .status(memberMission.getStatus().name())
                        .build())
                .collect(Collectors.toList());

        // 페이징 처리
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), challengingMissions.size());

        // Page 객체 생성 및 반환
        return new PageImpl<>(challengingMissions.subList(start, end), pageable, challengingMissions.size());
    }




}