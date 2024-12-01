package umc.study.domain.enums;

public enum Gender {
    MALE(1),
    FEMALE(2),
    NONE(3);

    private final int code;

    Gender(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Gender fromCode(int code) {
        for (Gender gender : values()) {
            if (gender.getCode() == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender code: " + code);
    }
}
