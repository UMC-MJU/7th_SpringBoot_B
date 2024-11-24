package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestDTO {

    @Getter
    public static class ReveiwDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }


    private StoreDTO store;
    private RegionDTO region;



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreDTO {
        @NotBlank
        String address;

        @NotBlank
        String name;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegionDTO {
        @NotBlank
        String name;
    }




}
