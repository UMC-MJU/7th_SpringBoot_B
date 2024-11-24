package com.example.study.service.mission;

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
import com.example.study.web.dto.MissionRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedList;

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

    public Mission find(Long id){
        return missionRepository.findById(id).orElseThrow(()-> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
    }
}
