package umc.study.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.Role;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

@Getter
@Setter
public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {

        @NotBlank(message = "Name is mandatory")
        private String name;

        @NotNull(message = "Gender is mandatory")
        private int gender;

        @NotNull(message = "Birth year is mandatory")
        private Integer birthYear;

        @NotNull(message = "Birth month is mandatory")
        private Integer birthMonth;

        @NotNull(message = "Birth day is mandatory")
        private Integer birthDay;

        @Size(min = 5, max = 12)
        @NotBlank(message = "Address is mandatory")
        private String address;

        @Size(min = 5, max = 12)
        private String specAddress;

        @ExistCategories
        @NotNull(message = "Prefer categories are mandatory")
        private List<Long> preferCategory;

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank
        String password;    // 비밀번호 필드 추가

        @NotNull
        Role role;    // 역할 필드 추가

        // 새로운 point 필드 추가
        private Integer point = 0;  // 기본값 0을 설정

        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }
    }
}
