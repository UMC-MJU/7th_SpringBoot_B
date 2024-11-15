package com.example.study.repository.store;

import com.example.study.domain.*;
import com.example.study.domain.enums.MissionStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QStore store = QStore.store;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    private final QMember member = QMember.member;
    private final QReview review = QReview.review;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(store.name.eq(name));
        }

        if (score != null) {
            //4.0f 이상인지 확인
            predicate.and(store.score.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }

}
