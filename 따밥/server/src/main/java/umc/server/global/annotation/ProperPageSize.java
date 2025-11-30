package umc.server.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.server.global.validator.PageSizeValidator;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Constraint(validatedBy = PageSizeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProperPageSize {
    String message() default "요청한 페이지 사이즈가 0보다 커야 합니다."; // 유효하지 않을 경우 반환할 메시지
    Class<?>[] groups() default {}; // 유효성 검증이 진행될 그룹
    Class<? extends Payload>[] payload() default {}; // 유효성 검증 시에 전달할 메타 정보
}
