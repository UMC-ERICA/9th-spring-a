package umc.server.global.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.server.global.annotation.ProperPageSize;

public class PageSizeValidator implements ConstraintValidator<ProperPageSize, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        return page >= 0;
    }
}
