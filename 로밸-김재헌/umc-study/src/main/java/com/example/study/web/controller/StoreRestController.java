package com.example.study.web.controller;

import com.example.study.apiPayload.ApiResponse;
import com.example.study.converter.StoreConverter;
import com.example.study.domain.Member;
import com.example.study.domain.Review;
import com.example.study.service.Review.ReviewService;
import com.example.study.service.member.MemberCommandService;
import com.example.study.service.mission.MissionService;
import com.example.study.service.store.StoreQueryService;
import com.example.study.validation.annotation.ExistStore;
import com.example.study.web.dto.MissionRequest;
import com.example.study.web.dto.ReviewRequest;
import com.example.study.web.dto.StoreResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final ReviewService reviewService;
    private final MemberCommandService memberCommandService;
    private final MissionService missionService;
    private final StoreQueryService storeQueryService;


    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponse.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

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
