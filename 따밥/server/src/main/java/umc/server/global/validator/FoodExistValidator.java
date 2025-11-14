package umc.server.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.server.domain.member.exception.FoodErrorCode;
import umc.server.domain.member.repository.FoodRepository;
import umc.server.global.annotation.ExistsFoods;

import java.util.List;
@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistsFoods, List<Long>> {
    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(foodRepository::existsById); // 모든 값이 DB에 존재하면 isValid = true

        if (!isValid){
            // 이 부분에서 아까 디폴트 메시지를 초기화 시키고, 새로운 메시지로 덮어씌우게 됩니다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.FOOD_NOT_FOUND.getMessage()).addConstraintViolation();
        }
        return false;
    }
}
