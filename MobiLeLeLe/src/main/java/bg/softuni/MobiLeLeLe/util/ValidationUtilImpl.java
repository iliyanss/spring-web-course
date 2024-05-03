package bg.softuni.MobiLeLeLe.util;

import org.springframework.stereotype.Component;

import jakarta.validation.Validation;
import jakarta.validation.Validator;


@Component
public class ValidationUtilImpl implements ValidationUtil {

    private final Validator validator;

    public ValidationUtilImpl() {
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }
}
