package com.example.jerry.global.validator;

import com.example.jerry.domain.repository.FoodRepository;
import com.example.jerry.domain.exception.code.FoodErrorCode;
import com.example.jerry.global.annotation.ExistFoods;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodExistValidator implements ConstraintValidator<ExistFoods, List<Integer>> {

    private final FoodRepository foodRepository;

    @Override
    public boolean isValid(List<Integer> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> foodRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(FoodErrorCode.FOOD_NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;

    }
}