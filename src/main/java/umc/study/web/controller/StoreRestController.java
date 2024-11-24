package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.service.StoreService.StoreService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import jakarta.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreService storeService;
    private final StoreQueryService storeQueryService;

    // 특정 지역에 가게 추가하기
    @PostMapping("/create")
    public ApiResponse<StoreResponseDTO> create(@RequestBody @Valid StoreRequestDTO storeRequestDTO) {
        Store store = storeService.addStoreToRegion(storeRequestDTO);
        return ApiResponse.onSuccess(StoreConverter.toStoreResponseDTO(store));
    }

    // 가게에 리뷰 추가하기
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO> review(@PathVariable Long storeId, @RequestBody @Valid ReviewRequestDTO reviewDTO) {
        Long memberId = 2L;
        Review review = storeService.addReviewToStore(reviewDTO, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResponseDTO(review));
    }

    // 리뷰 조회하기
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String으로 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@umc.study.validation.annotation.ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page")Integer page) {
        storeQueryService.getReviewList(storeId, page);
        return null;
    }


}
