package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityEnum;

public interface PriorityService {

    void initPriorities();

    Priority findPriority(PriorityEnum style);

    Priority findStyleByStyleName(PriorityEnum styleName);
}
