package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.ApiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<Review> addReview(@RequestBody @Valid ReviewRequestDTO request) {
        Review review = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(review);
    }
}
