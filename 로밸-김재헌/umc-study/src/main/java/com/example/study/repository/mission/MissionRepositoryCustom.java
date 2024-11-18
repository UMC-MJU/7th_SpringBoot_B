package com.example.study.repository.mission;

import com.example.study.domain.Member;
import com.example.study.domain.Mission;
import com.example.study.domain.Region;
import com.example.study.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    public List<Mission> findAll(MissionStatus status, Member member, int page, int size);
    public List<Mission> findChallengedMissionByRegion(Member member, Region region, int page, int size);

}
