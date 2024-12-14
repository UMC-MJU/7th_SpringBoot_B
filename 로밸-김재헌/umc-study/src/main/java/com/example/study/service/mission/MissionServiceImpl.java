package com.example.study.service.mission;

import com.example.study.converter.MissionConverter;
import com.example.study.domain.Member;
import com.example.study.domain.MemberMission;
import com.example.study.domain.Mission;
import com.example.study.domain.Store;
import com.example.study.domain.enums.MissionStatus;
import com.example.study.exception.ErrorStatus;
import com.example.study.exception.handler.MissionHandler;
import com.example.study.repository.MemberMissionRepository;
import com.example.study.repository.mission.MissionRepository;
import com.example.study.service.store.StoreCommandService;
import com.example.study.validation.annotation.MissionStartAble;
import com.example.study.web.dto.MemberMissionRequest;
import com.example.study.web.dto.MemberMissionResponse;
import com.example.study.web.dto.MissionRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;
    private final StoreCommandService storeCommandService;
    private final MemberMissionRepository memberMissionRepository;
    @Override
    public void register(Long storeId, MissionRequest.registerMission request) {
        Store store = storeCommandService.find(storeId);

        Mission mission = Mission.builder()
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadLine())
                .memberMissionList(new LinkedList<>())
                .reward(request.getReward())
                .store(store)
                .build();

        store.getMissions().add(mission);

        missionRepository.save(mission);
    }

    @Override
    public void registerUser(@Valid  @MissionStartAble MemberMissionRequest.StartMission startMission) {
        Member member = startMission.getMember();
        Mission mission = find(startMission.getMissionId());

        if(exist(member, mission)){
            throw new MissionHandler(ErrorStatus.MISSION_CONFLICT);
        }
        MemberMission memberMission = MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .member(member)
                .mission(mission)
                .build();
        member.getMemberMissionList().add(memberMission);
        mission.getMemberMissionList().add(memberMission);

        memberMissionRepository.save(memberMission);
    }

    @Override
    public boolean exist(Member member, Long missionId) {
        Mission mission = find(missionId);
        return memberMissionRepository.existsByMemberAndMission(member, mission);
    }

    @Override
    public boolean exist(Member member, Mission mission){
        return memberMissionRepository.existsByMemberAndMission(member, mission);
    }

    @Override
    public Page<MemberMissionResponse.memberMissionPreview> getMissions(Member member, String status, int page) {
        MissionStatus missionStatus = MissionStatus.from(status);
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberAndStatus(
                member, missionStatus, PageRequest.of(page, 10));

        List<MemberMissionResponse.memberMissionPreview> previews =  memberMissionPage.stream()
                .map(MissionConverter::toMemberMissionPreview)
                .toList();

        return new PageImpl<>(previews, memberMissionPage.getPageable(), memberMissionPage.getTotalElements());
    }

    public Mission find(Long id){
        return missionRepository.findById(id).orElseThrow(()-> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
    }
}
