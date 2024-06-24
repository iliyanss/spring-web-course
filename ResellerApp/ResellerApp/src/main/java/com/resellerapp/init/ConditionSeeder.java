package com.resellerapp.init;

import com.resellerapp.model.entity.ConditionEntity;
import com.resellerapp.model.entity.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;

@Component
public class ConditionSeeder implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    public ConditionSeeder(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.conditionRepository.count() != 0) {
            return;
        }
        Arrays.stream(ConditionEnum.values())
                .forEach(l -> {
                    ConditionEntity conditionEntity = new ConditionEntity();
                    conditionEntity.setConditionName(l);
                    switch (l.getValue()) {
                        case "Excellent":
                            conditionEntity.setDescription("In perfect condition.");
                            break;
                        case "Good":
                            conditionEntity.setDescription("Some signs of wear and tear or minor defects.");
                            break;
                        case "Acceptable":
                            conditionEntity.setDescription("The item is fairly worn but continues to function properly.");
                            break;
                    }
                    this.conditionRepository.save(conditionEntity);
                });
    }
}
