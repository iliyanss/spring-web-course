package com.resellerapp.service;

import com.resellerapp.model.entity.ConditionEntity;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public ConditionEntity findCondition(ConditionEnum conditionEnum) {
        return this.conditionRepository.findByConditionName(conditionEnum).orElseThrow();
    }
}
