package com.example.study.repository.mission;

import com.example.study.domain.*;
import com.example.study.domain.enums.MissionStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MissionRepositoryImpl implements MissionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    public List<Mission> findAll(MissionStatus status, Member member, int page, int size) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (member != null) {
            predicate.and(memberMission.member.eq(member));
        }

        if (status == MissionStatus.CHALLENGING) {
            predicate.and(memberMission.status.eq(MissionStatus.CHALLENGING));
        } else if (status == MissionStatus.COMPLETE) {
            predicate.and(memberMission.status.eq(MissionStatus.COMPLETE));
        }

        return jpaQueryFactory
                .selectFrom(mission)
                .join(memberMission.mission, mission)
                .where(predicate)
                .limit(size)
                .offset((page - 1) * size)
                .fetch();
    }

    public List<Mission> findChallengedMissionByRegion(Member member, Region region, int page, int size) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (member != null) {
            predicate.and(memberMission.member.eq(member));
        }

        if(region != null){
            predicate.and(store.region.eq(region));
        }


        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store)
                .where(predicate)
                .limit(size)
                .offset((page - 1) * size)
                .fetch();
    }
}
