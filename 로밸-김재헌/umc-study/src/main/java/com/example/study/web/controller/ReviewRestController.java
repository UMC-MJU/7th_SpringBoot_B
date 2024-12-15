package com.example.study.web.controller;

import com.example.study.apiPayload.ApiResponse;
import com.example.study.converter.StoreConverter;
import com.example.study.domain.Member;
import com.example.study.domain.Review;
import com.example.study.service.Review.ReviewService;
import com.example.study.service.member.MemberCommandService;
import com.example.study.web.dto.StoreResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewRestController {
    private final MemberCommandService memberCommandService;
    private final ReviewService reviewService;

    @GetMapping("/my")
    @Operation(summary = "내 리뷰 조회 api")
    public ApiResponse<StoreResponse.ReviewPreViewListDTO> addMission(@RequestParam(name = "page")int page){
        Member member = memberCommandService.find(1L);
        Page<Review> reviews = reviewService.getMyReviews(member, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviews));
    }
}
