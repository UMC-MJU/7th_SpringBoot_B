package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.*;

//import static umc.study.service.MemberService.MemberCommandService.storeQueryService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 회원이 작성한 리뷰 목록을 조회하는 API입니다. 페이징을 포함합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "page", description = "페이지 번호",  example = "1"),
            @Parameter(name = "size", description = "페이지 크기 (한 페이지에 몇 개의 미션을 가져올지)", example = "10")
    })
    public ApiResponse<Page<ReviewResponseDTO>> getMyReviews(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page)
    {
        Page<ReviewResponseDTO> reviews = memberQueryService.getMyReviews(memberId, page - 1, 10);
        return ApiResponse.onSuccess(reviews);
    }
    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{memberId}/missions/{missionId}/challenge")
    @Operation(summary = "미션 도전 API", description = "특정 회원이 특정 미션에 도전하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION001", description = "미션이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER001", description = "회원이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!", example = "1"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!", example = "42")
    })
    public ApiResponse<MemberResponseDTO.MemberMissionPreViewDTO> challengeMission(
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "missionId") Long missionId) {
        MemberResponseDTO.MemberMissionPreViewDTO response = memberCommandService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "특정 회원이 진행 중인 미션 목록을 조회하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER001", description = "회원이 존재하지 않습니다.")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID", example = "1"),
            @Parameter(name = "page", description = "페이지 번호", example = "1"),
            @Parameter(name = "size", description = "페이지 크기", example = "10")
    })
    public ApiResponse<Page<MemberResponseDTO.MemberMissionDTO>> getMyChallengingMissions(
            @PathVariable Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page){
        Page<MemberResponseDTO.MemberMissionDTO> missions = memberCommandService.getMyChallengingMissions(memberId, page - 1, 10);
        return ApiResponse.onSuccess(missions);
    }


    @PostMapping("/{memberId}/missions/{missionId}/complete")
    @Operation(summary = "미션 완료 API", description = "특정 회원이 특정 미션을 완료 상태로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK, 성공", content = @Content(schema = @Schema(implementation = MemberMissionResponseDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "missionId", description = "미션 ID")
    })
    public ApiResponse<MemberMissionResponseDTO> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId) {
        MemberMissionResponseDTO response = memberCommandService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess(response);
    }
}




