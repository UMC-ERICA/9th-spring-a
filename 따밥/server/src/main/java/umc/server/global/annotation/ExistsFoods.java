package umc.server.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.server.global.validator.FoodExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FoodExistValidator.class)
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsFoods {
    // @interface : 커스텀 어노테이션 만들기
    String message() default "해당 음식이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
