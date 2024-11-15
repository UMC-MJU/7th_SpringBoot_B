package com.example.study.repository.review;

import com.example.study.domain.QReview;
import com.example.study.domain.Review;
import com.example.study.domain.Store;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;

    public List<Review> findAllByStore(Store store, int page, int size) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (store != null) {
            predicate.and(review.store.eq(store));
        }

        return jpaQueryFactory
                .selectFrom(review)
                .where(predicate)
                .limit(size)
                .offset((page - 1) * size)
                .fetch();
    }
}
