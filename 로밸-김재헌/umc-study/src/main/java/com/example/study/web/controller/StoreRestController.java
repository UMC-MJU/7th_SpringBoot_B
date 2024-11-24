package com.example.study.web.controller;

import com.example.study.apiPayload.ApiResponse;
import com.example.study.domain.Member;
import com.example.study.service.Review.ReviewService;
import com.example.study.service.member.MemberCommandService;
import com.example.study.service.mission.MissionService;
import com.example.study.validation.annotation.ExistStore;
import com.example.study.web.dto.MissionRequest;
import com.example.study.web.dto.ReviewRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final ReviewService reviewService;
    private final MemberCommandService memberCommandService;
    private final MissionService missionService;


    @PostMapping("/{store-id}")
    @Operation(summary = "미션 추가 api")
    public ApiResponse<HttpStatus> addMission(
            @PathVariable(name = "store-id") @Valid @ExistStore Long storeId,
            @RequestBody @Valid MissionRequest.registerMission request){
        missionService.register(storeId, request);
        return ApiResponse.onSuccess(HttpStatus.OK);
    }

    @PostMapping("/{store-id}/review")
    @Operation(summary = "리뷰 등록 api")
    public ApiResponse<HttpStatus> addReview(
            @PathVariable(name = "store-id") @Valid @ExistStore Long storeId,
            @RequestBody @Valid ReviewRequest.registerReview request){

        Member member = memberCommandService.find(1L);
        reviewService.register(member, storeId, request);
        //dto로 등록된 리뷰 객체를 response를 해주는 것도 좋겠지만 시간상 생략
        return ApiResponse.onSuccess(HttpStatus.OK);
    }


}
