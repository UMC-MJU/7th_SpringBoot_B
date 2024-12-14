package com.example.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ReviewRequest {

    @Getter
    public static class registerReview{
        private String title;
        private Float score;
        private String body;
    }

}
