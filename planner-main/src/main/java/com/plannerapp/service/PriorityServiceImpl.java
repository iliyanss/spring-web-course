package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;
import com.plannerapp.repo.PriorityRepo;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepo priorityRepo;

    public PriorityServiceImpl(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @Override
    public void initPriorities() {
        if (this.priorityRepo.count() != 0) {
            return;
        }

        Arrays.stream(PriorityEnum.values())
                .forEach(s -> {
                    Priority priority = new Priority();
                    priority.setPriorityName(s);
                    switch (s.getValue()) {
                        case "Urgent":
                            priority.setDescription("An urgent problem that blocks the system use until the issue is resolved.");
                            break;
                        case "Important":
                            priority.setDescription("A core functionality that your product is explicitly supposed to perform is compromised.");
                            break;
                        default:
                            priority.setDescription("Should be fixed if time permits but can be postponed.");
                            break;
                    }

                    this.priorityRepo.save(priority);
                });

    }

    @Override
    public Priority findPriority(PriorityEnum priorityEnum) {
        return this.priorityRepo.findByPriorityName(priorityEnum).orElseThrow();
    }

    @Override
    public Priority findStyleByStyleName(PriorityEnum styleName) {
        return this.priorityRepo.findByPriorityName(styleName).orElseThrow();
    }
}
