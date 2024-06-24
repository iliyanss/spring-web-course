package com.plannerapp.vallidation;

import com.plannerapp.service.TaskServiceImpl;
import com.plannerapp.vallidation.annotation.UniquePerformer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePerformerValidator implements ConstraintValidator<UniquePerformer, String> {

    private final TaskServiceImpl songService;

    public UniquePerformerValidator(TaskServiceImpl songService) {
        this.songService = songService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
