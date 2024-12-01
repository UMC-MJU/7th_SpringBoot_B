package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDTO {
    private Long reviewId;
    private String title;
    private String body;
    private Float score;
    private LocalDateTime createdAt;
}
