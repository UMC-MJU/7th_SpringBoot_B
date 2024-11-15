package com.example.study.repository.review;

import com.example.study.domain.Review;
import com.example.study.domain.Store;

import java.util.List;

public interface ReviewRepositoryCustom {

    public List<Review> findAllByStore(Store store, int page, int size);
}
